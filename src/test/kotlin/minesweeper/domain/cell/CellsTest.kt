package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import minesweeper.domain.cell.CellType.MINE
import minesweeper.domain.cell.CellType.ONE
import minesweeper.domain.cell.CellType.ZERO
import minesweeper.domain.strategy.RandomMinePlacementStrategy

class CellsTest : FunSpec({

    context("placeMine") {
        test("지뢰 갯수가 0보다 작은 경우 예외가 발생한다.") {
            val cells = Cells(listOf(Cell(0, 0)))

            val exception =
                shouldThrowExactly<IllegalArgumentException> { cells.placeMine(0, RandomMinePlacementStrategy()) }
            exception shouldHaveMessage "지뢰 갯수는 1이상이어야 합니다."
        }

        test("지뢰 갯수가 cell 갯수보다 큰 경우 예외가 발생한다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, isDisplay = true),
                    Cell(0, 1, isDisplay = true),
                    Cell(1, 0, isDisplay = true),
                    Cell(1, 1, isDisplay = true),
                ),
            )

            val exception =
                shouldThrowExactly<IllegalArgumentException> { cells.placeMine(4, RandomMinePlacementStrategy()) }
            exception shouldHaveMessage "지뢰 갯수는 현재 cell크기 4보다 작은 값을 입력하여야 합니다."
        }

        test("이미 지뢰가 배치되어 있는 경우 예외가 발생한다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, MINE, isDisplay = true),
                    Cell(0, 1, isDisplay = true),
                    Cell(1, 0, isDisplay = true),
                    Cell(1, 1, isDisplay = true),
                ),
            )

            val exception =
                shouldThrowExactly<IllegalStateException> { cells.placeMine(1, RandomMinePlacementStrategy()) }
            exception shouldHaveMessage "이미 지뢰가 배치되어 있습니다."
        }

        test("지뢰를 배치하고 지뢰 근처의 카운트를 갯수에 맞게 올린다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, isDisplay = true),
                    Cell(0, 1, isDisplay = true),
                    Cell(0, 2, isDisplay = true),
                    Cell(1, 0, isDisplay = true),
                    Cell(1, 1, isDisplay = true),
                    Cell(1, 2, isDisplay = true),
                    Cell(2, 0, isDisplay = true),
                    Cell(2, 1, isDisplay = true),
                    Cell(2, 2, isDisplay = true),
                ),
            )
            cells.placeMine(1) { cells.values[Coordinate(0, 0)]!! }

            val actual = cells.values.count { it.value.isMine() }
            actual shouldBe 1
            cells.values[Coordinate(0, 0)]!!.openCellType() shouldBe MINE
            cells.values[Coordinate(0, 1)]!!.openCellType() shouldBe ONE
            cells.values[Coordinate(0, 2)]!!.openCellType() shouldBe ZERO
            cells.values[Coordinate(1, 0)]!!.openCellType() shouldBe ONE
            cells.values[Coordinate(1, 1)]!!.openCellType() shouldBe ONE
            cells.values[Coordinate(1, 2)]!!.openCellType() shouldBe ZERO
            cells.values[Coordinate(2, 0)]!!.openCellType() shouldBe ZERO
            cells.values[Coordinate(2, 1)]!!.openCellType() shouldBe ZERO
            cells.values[Coordinate(2, 2)]!!.openCellType() shouldBe ZERO
        }

        test("랜덤한 위치에 지뢰를 배치한다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, isDisplay = true),
                    Cell(0, 1, isDisplay = true),
                    Cell(1, 0, isDisplay = true),
                    Cell(1, 1, isDisplay = true),
                ),
            )
            cells.placeMine(2, RandomMinePlacementStrategy())
            val actual = cells.values.count { it.value.isMine() }
            actual shouldBe 2
        }
    }

    context("open") {
        test("존재하지 않는 좌표가 입력되면 예외가 발생한다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, ONE),
                    Cell(0, 1, ONE),
                    Cell(1, 0, ONE),
                    Cell(1, 1, MINE),
                ),
            )
            val exception = shouldThrowExactly<IllegalArgumentException> { cells.open(Coordinate(2, 2)) }
            exception shouldHaveMessage "존재하지 않는 좌표는 입력될 수 없습니다."
        }

        test("현재 좌표가 0이 아니라면 현재 좌표만 오픈한다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, ONE),
                    Cell(0, 1, ONE),
                    Cell(1, 0, ONE),
                    Cell(1, 1, MINE),
                ),
            )
            cells.open(Coordinate(1, 1))

            cells.values[Coordinate(0, 0)]!!.isDisplay shouldBe false
            cells.values[Coordinate(0, 1)]!!.isDisplay shouldBe false
            cells.values[Coordinate(1, 0)]!!.isDisplay shouldBe false
            cells.values[Coordinate(1, 1)]!!.isDisplay shouldBe true
        }

        test("현재의 좌표가 열리고, 0이면 지뢰를 제외한 모든 좌표가 열린다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, ZERO),
                    Cell(0, 1, ZERO),
                    Cell(0, 2, ZERO),
                    Cell(1, 0, ZERO),
                    Cell(1, 1, ONE),
                    Cell(1, 2, ONE),
                    Cell(2, 0, ZERO),
                    Cell(2, 1, ONE),
                    Cell(2, 2, MINE),
                ),
            )
            val actual = cells.open(Coordinate(0, 0))

            actual shouldBe 8
            cells.values[Coordinate(0, 0)]!!.isDisplay shouldBe true
            cells.values[Coordinate(0, 1)]!!.isDisplay shouldBe true
            cells.values[Coordinate(0, 2)]!!.isDisplay shouldBe true
            cells.values[Coordinate(1, 0)]!!.isDisplay shouldBe true
            cells.values[Coordinate(1, 1)]!!.isDisplay shouldBe true
            cells.values[Coordinate(1, 2)]!!.isDisplay shouldBe true
            cells.values[Coordinate(2, 0)]!!.isDisplay shouldBe true
            cells.values[Coordinate(2, 1)]!!.isDisplay shouldBe true
            cells.values[Coordinate(2, 2)]!!.isDisplay shouldBe false
        }

        test("0이 아닌 일반 좌표가 열리면 1을 반환한다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, ONE),
                    Cell(0, 1, ONE),
                    Cell(1, 0, ONE),
                    Cell(1, 1, MINE),
                ),
            )
            val actual = cells.open(Coordinate(1, 0))
            actual shouldBe 1
        }

        test("열린 좌표가 mine이면 1을 반환한디.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, ONE),
                    Cell(0, 1, ONE),
                    Cell(1, 0, ONE),
                    Cell(1, 1, MINE),
                ),
            )
            val actual = cells.open(Coordinate(1, 1))
            actual shouldBe 0
        }
    }
})
