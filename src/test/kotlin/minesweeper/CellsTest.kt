package minesweeper

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.Cell
import minesweeper.model.Cells


class CellsTest : StringSpec({
    "지뢰찾기 셀 표시 숫자 테스트" {
        val cells = Cells(
            listOf(
                Cell(0, 0),
                Cell(0, 1),
                Cell(0, 2),
                Cell(0, 3),
                Cell(1, 0),
                Cell(1, 1),
                Cell(1, 2),
                Cell(1, 3),
            )
        )

        cells.cellList[0].addMine()
        cells.addMineAroundCounts()

        cells.cellList[1].mineAroundCount() shouldBe 1
    }
    "지뢰찾기 셀 표시 숫자 2 테스트" {
        val cells = Cells(
            listOf(
                Cell(0, 0),
                Cell(0, 1),
                Cell(0, 2),
                Cell(0, 3),
                Cell(1, 0),
                Cell(1, 1),
                Cell(1, 2),
                Cell(1, 3),
            )
        )

        cells.cellList[0].addMine()
        cells.cellList[2].addMine()
        cells.addMineAroundCounts()

        cells.cellList[1].mineAroundCount() shouldBe 2
    }
})
