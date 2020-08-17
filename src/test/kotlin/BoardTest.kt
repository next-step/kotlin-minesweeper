import game.Board
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BoardTest {

    @ParameterizedTest
    @ValueSource(strings = ["0 0\n0 0\n0 0\n"])
    fun `높이 * 너비 크기의 판을 생성한다`(board: String) {
        assertThat(Board(2, 3, 0).toString()).isEqualTo(board)
    }

    @ParameterizedTest
    @ValueSource(ints = [101, -1])
    fun `지뢰의 개수는 0 이상 (높이 * 너비) 이하여야 한다`(mineCount: Int) {
        assertThat(Board.isValidMineCount(10, 10, mineCount)).isFalse()
        assertThat(Board(10, 10, mineCount)).isNull()
    }

    @ParameterizedTest
    @ValueSource(ints = [3, 5, 10])
    fun `지뢰는 X로 표시한다`(mineCount: Int) {
        assertThat(
            Board(10, 10, mineCount).toString().count { it.toString() == "X" }
        ).isEqualTo(mineCount)
    }

    @ParameterizedTest
    @ValueSource(ints = [10])
    fun `지뢰가 아닐 경우 주변 8개 사각형에 포함된 지뢰의 개수를 표시한다`(mineCount: Int) {
        val boardString = Board(10, 5, mineCount).toString()
        assertThat(
            boardString.split(" ", "\n").filter { it.isNotBlank() && it != "X" }
        ).allSatisfy { it.toInt() in 0..mineCount }
    }
}
