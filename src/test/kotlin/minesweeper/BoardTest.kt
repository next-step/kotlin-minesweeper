package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.data.PositiveInt
import minesweeper.domain.game.Board

class BoardTest : StringSpec({
    " mine 갯수는 총 Cell 갯수보다 적어야 합니다." {
        shouldThrow<IllegalArgumentException> {
            val row = PositiveInt(5)
            val col = PositiveInt(5)
            val mineCount = PositiveInt(26)
            Board(row, col, mineCount)
        }
    }

    " row 5 cal 5 총 Cell 25" {
        val row = PositiveInt(5)
        val col = PositiveInt(5)
        val mineCount = PositiveInt(5)

        val board = Board(row, col, mineCount)
        board.cells.size shouldBe 5
        board.cells[0].size shouldBe 5
        board.cells.flatten().size shouldBe 25
    }
})
