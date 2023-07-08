package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
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
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(1, 0),
                    Cell(1, 1),
                ),
            )

            val exception =
                shouldThrowExactly<IllegalArgumentException> { cells.placeMine(4, RandomMinePlacementStrategy()) }
            exception shouldHaveMessage "지뢰 갯수는 현재 cell크기 4보다 작은 값을 입력하여야 합니다."
        }

        test("이미 지뢰가 배치되어 있는 경우 예외가 발생한다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0, CellType.MINE),
                    Cell(0, 1),
                    Cell(1, 0),
                    Cell(1, 1),
                ),
            )

            val exception =
                shouldThrowExactly<IllegalStateException> { cells.placeMine(1, RandomMinePlacementStrategy()) }
            exception shouldHaveMessage "이미 지뢰가 배치되어 있습니다."
        }

        test("지뢰를 배치하고 지뢰 근처의 카운트를 갯수에 맞게 올린다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(0, 2),
                    Cell(1, 0),
                    Cell(1, 1),
                    Cell(1, 2),
                    Cell(2, 0),
                    Cell(2, 1),
                    Cell(2, 2),
                ),
            )
//            val cells = Cells(cells = cells,
//            ) { cells[0] }
            cells.placeMine(1) { cells.values[0] }

            val actual = cells.values.count { it.isMine() }
            actual shouldBe 1
            cells.values[0].cellType shouldBe CellType.MINE
            cells.values[1].cellType shouldBe CellType.ONE
            cells.values[2].cellType shouldBe CellType.ZERO
            cells.values[3].cellType shouldBe CellType.ONE
            cells.values[4].cellType shouldBe CellType.ONE
            cells.values[5].cellType shouldBe CellType.ZERO
            cells.values[6].cellType shouldBe CellType.ZERO
            cells.values[7].cellType shouldBe CellType.ZERO
            cells.values[8].cellType shouldBe CellType.ZERO
        }

        test("랜덤한 위치에 지뢰를 배치한다.") {
            val cells = Cells(
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(1, 0),
                    Cell(1, 1),
                ),
            )
            cells.placeMine(2, RandomMinePlacementStrategy())
            val actual = cells.values.count { it.isMine() }

            actual shouldBe 2
        }
    }
})
