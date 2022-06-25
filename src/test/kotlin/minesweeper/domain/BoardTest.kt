package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardTest {

    @Test
    fun `10 x 10 Board는 100개의 cell을 가진다`() {
        val board = Board(BoardSize(10, 10), 0)

        assertThat(board.cells).hasSize(100)
    }

    @Test
    fun `너비는 0이면 안된다`() {
        assertThrows<IllegalArgumentException> { Board(BoardSize(10, 0), 0) }
    }

    @Test
    fun `높이는 0이면 안된다`() {
        assertThrows<IllegalArgumentException> { Board(BoardSize(0, 10), 0) }
    }

    @Test
    fun `지뢰 갯수는 넓이보다 작아야 한다`() {
        assertThrows<IllegalArgumentException> { Board(BoardSize(10, 10), 100) }
    }
}
