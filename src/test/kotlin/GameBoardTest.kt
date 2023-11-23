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

    @Test
    fun `지뢰를 랜덤으로 심는다`() {
        // given
        val board = GameBoard.of(2, 2)

        // given, when
        val plantedBoard = board.plantMines(1)

        // when
        plantedBoard.cellMatrix.stream()
            .flatMap { it.cellCollection.stream() }
            .filter { it.value == MineStatus.MINE }
            .count() shouldBe 1
    }
}
