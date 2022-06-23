package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoardTest {

    @Test
    fun `10 x 10 Board는 100개의 cell을 가진다`() {
        val board = Board(10, 10, 0)

        assertThat(board.cells).hasSize(100)
    }
}
