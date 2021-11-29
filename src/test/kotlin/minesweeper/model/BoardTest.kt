package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardTest {

    @Test
    fun `보드는 Width와 Height를 가진다`() {
        val board = Board(width = 5, height = 10)
        assertAll(
            { assertThat(board.width).isEqualTo(Width.valueOf(5)) },
            { assertThat(board.height).isEqualTo(Height.valueOf(10)) },
        )
    }

    @Test
    fun `보드는 MineCount를 가진다`() {
        val board = Board(width = 2, height = 2, mineCount = 4)
        assertThat(board.mineCount).isEqualTo(MineCount.valueOf(4))
    }

    @Test
    fun `Width나 Height이 0이면 보드는 비어있다`() {
        val board = Board(width = 0, height = 2)
        assertThat(board).isEqualTo(Board.EMPTY)
    }

    @Test
    fun `Width * Height 크기의 비어있는 보드를 만들 수 있다`() {
        val position = Position(10, 10)
        val board = Board(width = 10, height = 10)
        assertThat(board.cells[position]).isEqualTo(Cell.Zero(position))
    }

    @Test
    fun `Position의 Cell을 Mine으로 변환할 수 있다`() {
        val position = Position(1, 1)
        val board = Board(width = 1, height = 2)

        val actual = board.mine(position)
        assertThat(actual.cells[position]).isEqualTo(Cell.Mine(position))
    }

    @Test
    fun `Position의 Cell에 지뢰를 심으면 주변 Cell의 숫자가 증가한다`() {
        val board = Board(width = 2, height = 2)
        val actual = board.mine(Position(2, 2))
        assertAll(
            {
                assertThat(actual.cells[Position(1, 1)])
                    .isEqualTo(Cell.Number(MineCount.valueOf(1), Position(1, 1)))
            },
            {
                assertThat(actual.cells[Position(1, 2)])
                    .isEqualTo(Cell.Number(MineCount.valueOf(1), Position(1, 2)))
            }
        )
    }
}
