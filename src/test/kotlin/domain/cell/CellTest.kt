package domain.cell

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.CellMark
import minesweeper.domain.cell.Position

class CellTest : StringSpec({
    "셀 생성시 지뢰가 없다" {
        val cell = Cell(Position(3, 3))

        cell.mark shouldBe CellMark.EMPTY
    }
})
