package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun make_board_10x10() {
        val board = Board(10, 10)

        assertThat(board.area).isEqualTo(100)
    }
}
