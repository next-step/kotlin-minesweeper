package mine

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {

    @Test
    fun `너비와 높이 크기의 게임이 생성`() {
        val width = 10
        val height = 10
        val board = Board.createBoard(Width(width), Height(height))

        assertThat(board.width).isEqualTo(Width.value(width))
        assertThat(board.height).isEqualTo(Height.value(height))
    }

    @Test
    fun `너비와 높이의 값이 0보다 작은 경우 예외가 발생한다`() {
        val width = -1
        val height = 10

        assertThrows<IllegalArgumentException> { Board.createBoard(Width(width), Height(height)) }
    }
}
