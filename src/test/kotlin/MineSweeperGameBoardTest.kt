import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineSweeperGameBoardTest {

    @Test
    fun `보드판 생성`() {
        val strategy = object : BoardGenerator {
            override fun create(): List<Squares> {
                return listOf(
                    Squares(listOf(Mine(), NonMine())),
                    Squares(listOf(NonMine(), NonMine()))
                )
            }
        }

        val mineSweeperGameBoard = MineSweeperGameBoard(strategy)

        assertThat(mineSweeperGameBoard.board.size).isEqualTo(2)
    }
}
