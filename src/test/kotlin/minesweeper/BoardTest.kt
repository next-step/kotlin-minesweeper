package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.Board.Companion.MINE_COUNT_ILLEGAL_ARGUMENTS_EXCEPTION
import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.Width
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `Cells를 가지고 Board를 생성할 수 있다`() {
        val positions = Positions.of(Width.from(7), Height.Companion.from(7))
        val map: Map<Position, Cell> = positions.positions.associateWith { Cell.SafetyCell }

        val cells = Cells.from(map)
        val board = Board.from(cells)

        assertThat(board.cellList.size).isEqualTo(cells.cells.size)
    }

    @Test
    fun `Width, Height, MineCount를 가지고 Width * Height 크기의 Board를 생성할 수 있다`() {
        val width = Width.from(7)
        val height = Height.from(7)
        val mineCount = MineCount.from(7)

        val size = width.value * height.value
        val board = Board.of(width, height, mineCount)

        assertThat(board.cellList.size).isEqualTo(size)
    }

    @Test
    fun `Width, Height, MineCount를 가지고 Board를 만들 때 mineCount의 갯수는 width * height 이하여야 한다`() {
        val width = Width.from(7)
        val height = Height.from(7)
        val mineCount = MineCount.from(50)

        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Board.of(width, height, mineCount)
            }
            .withMessage(MINE_COUNT_ILLEGAL_ARGUMENTS_EXCEPTION)
    }
}
