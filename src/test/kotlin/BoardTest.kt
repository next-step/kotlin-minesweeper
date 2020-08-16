import game.Board
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BoardTest {

    @ParameterizedTest
    @ValueSource(strings = ["O O\nO O\nO O\n"])
    fun `높이 * 너비 크기의 판을 생성한다`(board: String) {
        assertThat(Board(2, 3, 0).toString()).isEqualTo(board)
    }

    @ParameterizedTest
    @ValueSource(ints = [101, -1])
    fun `지뢰의 개수는 0 이상 (높이 * 너비) 이하여야 한다`(mineCount: Int) {
        assertThat(Board.isValidMineCount(10, 10, mineCount)).isFalse()
        assertThat(Board(10, 10, mineCount)).isNull()
    }

    @Test
    fun `지뢰는 X로 표시한다`() {
        assertThat(
            Board(10, 10, 5).toString().count { it.toString() == "X" }
        ).isEqualTo(5)
    }
}
