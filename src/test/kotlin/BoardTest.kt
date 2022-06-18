import domain.Board
import domain.BoardItem
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

        assertThat(board.rows[1][4] is BoardItem.Normal).isTrue
        assertThat(board.rows[2][8] is BoardItem.Normal).isTrue

        val first = board.rows[1][4] as BoardItem.Normal
        assertThat(first.nearMineCount).isEqualTo(2)

        val second = board.rows[2][8] as BoardItem.Normal
        assertThat(second.nearMineCount).isEqualTo(1)
    }
}
