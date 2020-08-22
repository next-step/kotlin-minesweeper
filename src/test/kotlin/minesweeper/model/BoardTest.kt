package minesweeper.model

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `Board row-col Test`() {
        val board = Board(5, 6)

        Assertions.assertThat(board.getRow(1)).isEqualTo(0)
        Assertions.assertThat(board.getCol(1)).isEqualTo(1)

        Assertions.assertThat(board.getRow(4)).isEqualTo(0)
        Assertions.assertThat(board.getCol(4)).isEqualTo(4)

        Assertions.assertThat(board.getRow(29)).isEqualTo(4)
        Assertions.assertThat(board.getCol(29)).isEqualTo(5)

        Assertions.assertThat(board.getTotal()).isEqualTo(29)
    }
}
