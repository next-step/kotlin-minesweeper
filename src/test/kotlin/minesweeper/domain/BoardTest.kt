package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardTest {

    @Test
    fun `1 x 1 Board는 1개의 지뢰를 가질 수 없다`() {
        assertThrows<IllegalArgumentException> { Board(BoardSize(1, 1), 1) }
    }

    @Test
    fun `1 x 1 Board는 0개의 지뢰를 가질 수 있다`() {
        val board = Board(BoardSize(1, 1), 0)

        assertThat(board.cells).hasSize(1)
    }

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

    @Test
    fun `1 x 1 Board를 open할 수 있다`() {
        val board = Board(BoardSize(1, 1), 0)

        board.open()

        assertThat(board.rows().first().first()).isEqualTo(Opened(0))
    }

    @Test
    fun `2 x 2 Board with 1 Mine`() {
        val board = createBoard(BoardSize(2, 2), 1)

        board.open()

        assertThat(board.cells).isEqualTo(listOf(Mine, Opened(1), Opened(1), Opened(1)))
    }

    @Test
    fun `2 x 2 Board with 2 Mines`() {
        val board = createBoard(BoardSize(2, 2), 2)

        board.open()

        assertThat(board.cells).isEqualTo(listOf(Mine, Mine, Opened(2), Opened(2)))
    }

    @Test
    fun `2 x 2 Board with 3 Mines`() {
        val board = createBoard(BoardSize(2, 2), 3)

        board.open()

        assertThat(board.cells).isEqualTo(listOf(Mine, Mine, Mine, Opened(3)))
    }

    companion object {
        private fun createBoard(boardSize: BoardSize, minesCount: Int): Board {
            return Board(
                boardSize = boardSize,
                cells = (1..boardSize.area).map {
                    if (it <= minesCount) Mine
                    else Opened()
                }
            )
        }
    }
}
