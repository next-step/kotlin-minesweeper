import game.Board
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `높이 * 너비 크기의 판을 생성한다`() {
        assertThat(Board(2, 3, 0).toString()).isEqualTo("ㅁㅁ\nㅁㅁ\nㅁㅁ\n")
    }

    @Test
    fun `지뢰는 X로 표시한다`() {
        assertThat(
            Board(10, 10, 5).toString().count { it.toString() == "X" }
        ).isEqualTo(5)
    }

    @Test
    fun `지뢰의 개수는 0 이상 (높이 * 너비) 이하여야 한다`() {
        assertThat(Board(10, 10, 101)).isNull()
    }
}
