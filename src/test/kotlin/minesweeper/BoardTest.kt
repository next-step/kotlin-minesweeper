package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardTest {

    @Test
    fun `board 생성`() {
        val staticMinePositions = fun (width: Int, height: Int, mineCount: Int): List<Position> {
            return listOf(Position(0, 0))
        }

        val board = Board.createBoard(2, 2, 1, staticMinePositions)
        assertThat(board.cellRows[0].cells[0]).isEqualTo(Cell(true))
    }
}
