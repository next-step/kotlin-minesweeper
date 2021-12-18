package mine_tdd

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class MineTest {
    @Test
    fun `너비가 5인 객체가 생성된다`() {
        val widthVal = 5
        val width = Width(widthVal)

        Assertions.assertThat(width.value()).isEqualTo(widthVal)
    }

    @Test
    fun `높이가 5인 객체가 생성된다`() {
        val heightVal = 5
        val height = Height(heightVal)

        Assertions.assertThat(height.value()).isEqualTo(heightVal)
    }

    @Test
    fun `너비가 5, 높이가 5 크기의 게임이 보드가 생성된다`() {
        val width = 5
        val height = 5
        val board = Board.createBoard(Width(width), Height(height))

        Assertions.assertThat(board.row()).isEqualTo(width)
        Assertions.assertThat(board.column()).isEqualTo(height)
    }
}
