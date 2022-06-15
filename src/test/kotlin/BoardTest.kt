import domain.Board
import domain.MinePosition
import domain.Mines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun `지뢰 주변의 숫자가 근처 지뢰개수를 반영해야한다`() {
        val mines = Mines(
            listOf(
                MinePosition(0, 3),
                MinePosition(1, 3),
                MinePosition(2, 7)
            ).toSet()
        )

        val board = Board(3, 10, mines)

        assertThat(board.rows[1][4].getNearMineCount()).isEqualTo(2)
        assertThat(board.rows[2][8].getNearMineCount()).isEqualTo(1)
    }
}
