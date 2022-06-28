import domain.Point
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineSweeperGameBoardTest {

    @Test
    fun `보드판 생성`() {
        val strategy = object : BoardGenerator {
            override fun create(): Map<Point, Square> {
                return mapOf(
                    Point(0, 0) to Mine(),
                    Point(1, 0) to Mine(),
                    Point(0, 1) to Mine(),
                    Point(1, 1) to Mine()
                )
            }
        }

        val mineSweeperGameBoard = MineSweeperGameBoard(strategy)

        assertThat(mineSweeperGameBoard.board.size).isEqualTo(4)
    }
}
