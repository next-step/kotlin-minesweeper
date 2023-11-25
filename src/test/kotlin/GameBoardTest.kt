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
    fun `지뢰를 심는다`() {
        // given
        val board = GameBoard.of(2, 2)
        val points = listOf(
            Point(0, 0),
            Point(0, 1),
            Point(1, 0),
            Point(1, 1)
        )

        // given, when
        board.plantMines(points)

        // when
        board.cellMatrix[0][0].isMine() shouldBe true
        board.cellMatrix[0][1].isMine() shouldBe true
        board.cellMatrix[1][0].isMine() shouldBe true
        board.cellMatrix[1][1].isMine() shouldBe true
    }

    //  * 3 2 1 0
    //  3 * * 1 0
    //  2 * 3 1 0
    //  1 1 1 0 0
    //  0 0 0 0 0
    @Test
    fun `지뢰를 근거로 주변 지뢰 개수를 계산한다`() {
        // given
        val board = GameBoard.of(5, 5)
        val points = listOf(
            Point(0, 0),
            Point(1, 1),
            Point(1, 2),
            Point(2, 1)
        )
        board.plantMines(points)
        val expect = listOf(
            listOf(-1, 3, 2, 1, 0),
            listOf(3, -1, -1, 1, 0),
            listOf(2, -1, 3, 1, 0),
            listOf(1, 1, 1, 0, 0),
            listOf(0, 0, 0, 0, 0)
        )

        // given, when
        val actual = board.calculateMineCount()

        // when
        actual shouldBe expect
    }
}
