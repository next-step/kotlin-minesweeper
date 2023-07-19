package step4.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage
import step4.domain.CellType.MINE
import step4.domain.CellType.ONE
import step4.domain.CellType.ZERO
import java.util.LinkedList

class CellsTest : FunSpec({

    context("installMine") {
        test("입력된 지뢰 갯수가 0개 이하인 경우 예외가 발생한다.") {
            val cells = Cells(mapOf())
            val exception = shouldThrowExactly<IllegalArgumentException> { cells.installMine(0) { Cell() } }
            exception shouldHaveMessage "지뢰 갯수는 1개 이상이어야 합니다."
        }

        test("입력된 지뢰 갯수가 보유한 cell보다 많은 경우 예외가 발생한다.") {
            val cells = Cells(mapOf())
            val exception = shouldThrowExactly<IllegalArgumentException> { cells.installMine(1) { Cell() } }
            exception shouldHaveMessage "보유한 cell보다 많은 지뢰를 설치할 수 없습니다."
        }

        test("지뢰를 설치한다.") {
            val givenCells = listOf(Cell(), Cell(), Cell(), Cell())
            val cells = Cells(
                mapOf(
                    Coordinate(0, 0) to givenCells[0],
                    Coordinate(0, 1) to givenCells[1],
                    Coordinate(1, 0) to givenCells[2],
                    Coordinate(1, 1) to givenCells[3],
                ),
            )
            cells.installMine(1) { LinkedList(givenCells).poll() }

            val actual = cells.values[Coordinate(0, 0)]!!.isMine()
            actual shouldBe true
            givenCells[0].cellType shouldBe MINE
            givenCells[1].cellType shouldBe ONE
            givenCells[2].cellType shouldBe ONE
            givenCells[3].cellType shouldBe ONE
        }
    }

    context("open") {
        test("없는 좌표를 입력한 경우 예외가 발생한다.") {
            val cells = Cells(
                mapOf(
                    Coordinate(0, 0) to Cell(),
                ),
            )
            val exception = shouldThrowExactly<IllegalArgumentException> { cells.open(Coordinate(1, 1)) }
            exception shouldHaveMessage "존재하지 않는 좌표는 입력될 수 없습니다."
        }

        test("특정 좌표의 cell을 open한다.") {
            val coordinate = Coordinate(0, 0)
            val cells = Cells(
                mapOf(
                    coordinate to Cell(),
                ),
            )
            cells.open(coordinate)

            val actual = cells.values[coordinate]!!.isOpen
            actual shouldBe true
        }

        test("지뢰를 열었다면 0이 반환된다.") {
            val cells = Cells(
                mapOf(
                    Coordinate(0, 0) to Cell(cellType = MINE),
                    Coordinate(0, 1) to Cell(),
                    Coordinate(1, 0) to Cell(),
                    Coordinate(1, 1) to Cell(),
                ),
            )
            val actual = cells.open(Coordinate(0, 0))
            actual shouldBe 0
        }

        test("0이 아닌 일반좌표를 열었다면 1이 반환된다.") {
            val cells = Cells(
                mapOf(
                    Coordinate(0, 0) to Cell(cellType = ONE),
                    Coordinate(0, 1) to Cell(),
                    Coordinate(1, 0) to Cell(),
                    Coordinate(1, 1) to Cell(),
                ),
            )
            val actual = cells.open(Coordinate(0, 0))
            actual shouldBe 1
        }

        test("0을 열었다면 0근처의 좌표도 계속 열어 연만큼 반환된다.") {
            val cells = Cells(
                mapOf(
                    Coordinate(0, 0) to Cell(cellType = ZERO),
                    Coordinate(0, 1) to Cell(cellType = ZERO),
                    Coordinate(0, 2) to Cell(cellType = ZERO),
                    Coordinate(1, 0) to Cell(cellType = ZERO),
                    Coordinate(1, 1) to Cell(cellType = ONE),
                    Coordinate(1, 2) to Cell(cellType = ONE),
                    Coordinate(2, 0) to Cell(cellType = ZERO),
                    Coordinate(2, 1) to Cell(cellType = ONE),
                    Coordinate(2, 2) to Cell(cellType = MINE),
                ),
            )
            val actual = cells.open(Coordinate(0, 0))
            actual shouldBe 8
        }
    }
})
