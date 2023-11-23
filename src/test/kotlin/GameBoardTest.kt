import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameBoardTest {
    @Test
    fun `주어진 높이와 길이만큼 게임 판을 만든다`() {
        // given
        val height = 2
        val width = 2

        // given, when
        val board = GameBoard.of(height, width)

        // when
        board.cellMatrix.size shouldBe height
        board.cellMatrix[0].size shouldBe width
    }
}
