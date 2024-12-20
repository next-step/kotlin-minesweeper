package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import minesweeper.model.Board
import minesweeper.model.Cell
import minesweeper.model.Cells

class CellsTest : StringSpec({
    "지뢰찾기 셀 표시 숫자 테스트" {
        val cells =
            Cells(
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(0, 2),
                    Cell(0, 3),
                    Cell(1, 0),
                    Cell(1, 1),
                    Cell(1, 2),
                    Cell(1, 3),
                ),
            )

        cells.cellList[0].addMine()
        cells.addMinesAroundCounts()

        cells.cellList[1].mineAroundCount shouldBe 1
    }
    "지뢰찾기 셀 표시 숫자 2 테스트" {
        val cells =
            Cells(
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(0, 2),
                    Cell(0, 3),
                    Cell(1, 0),
                    Cell(1, 1),
                    Cell(1, 2),
                    Cell(1, 3),
                ),
            )

        cells.cellList[0].addMine()
        cells.cellList[2].addMine()
        cells.addMinesAroundCounts()

        cells.cellList[1].mineAroundCount shouldBe 2
    }
    "지뢰찾기 셀 표시 숫자 0 테스트" {
        val cells =
            Cells(
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(0, 2),
                    Cell(0, 3),
                    Cell(1, 0),
                    Cell(1, 1),
                    Cell(1, 2),
                    Cell(1, 3),
                ),
            )

        cells.cellList[0].addMine()
        cells.addMinesAroundCounts()

        cells.cellList[3].mineAroundCount shouldBe 0
    }
    "지뢰찾기 셀 로우 테스트" {
        val cells =
            Cells(
                listOf(
                    Cell(0, 0),
                    Cell(0, 1),
                    Cell(0, 2),
                    Cell(0, 3),
                    Cell(1, 0),
                    Cell(1, 1),
                    Cell(1, 2),
                    Cell(1, 3),
                ),
            )

        cells.getRowCells(0).cellList.size shouldBe 4

    }
    "지뢰찾기 오픈 1테스트" {
        val cells =
            Cells(
                listOf(
                    Cell(0, 0).apply { addMine() },
                    Cell(0, 1),
                    Cell(0, 2).apply { addMine() },
                    Cell(0, 3),
                    Cell(1, 0),
                    Cell(1, 1),
                    Cell(1, 2),
                    Cell(1, 3),
                ),
            )

        cells.addMinesAroundCounts()
        cells.openAroundCells(0,1)
        cells.cellList[1].isOpen shouldBe true
        cells.cellList[7].isOpen shouldBe false
    }
    "지뢰찾기 오픈 2 테스트" {
        val cells =
            Cells(
                listOf(
                    Cell(0, 0).apply { addMine() },
                    Cell(0, 1),
                    Cell(0, 2),
                    Cell(0, 3),
                    Cell(1, 0),
                    Cell(1, 1),
                    Cell(1, 2),
                    Cell(1, 3),
                ),
            )

        cells.addMinesAroundCounts()
        cells.openAroundCells(1,2)
        cells.cellList[5].isOpen shouldBe true
        cells.cellList[6].isOpen shouldBe true
        cells.cellList[7].isOpen shouldBe true
    }
    "지뢰찾기 오픈 예외 테스트" {
        val cells =
            Cells(
                listOf(
                    Cell(0, 0).apply { addMine() },
                    Cell(0, 1),
                    Cell(0, 2),
                    Cell(0, 3),
                    Cell(1, 0),
                    Cell(1, 1),
                    Cell(1, 2),
                    Cell(1, 3),
                ),
            )

        cells.addMinesAroundCounts()
        val exception =
            shouldThrow<IllegalArgumentException> {
                cells.openAroundCells(1,5)
            }
        exception.message should startWith("해당 셀이 없습니다.")
    }
})
