import domain.BoardItem
import domain.BoardRow
import domain.MinePosition
import domain.Mines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardRowTest {
    @Test
    fun `지뢰의 위치가 알맞은 칼럼에 있는지 테스트`() {
        val mines = Mines(
            listOf(
                MinePosition(0, 3),
                MinePosition(0, 7)
            ).toSet()
        )

        val row = BoardRow(0, 10, mines)
        assertThat(row.cells[3]).isEqualTo(BoardItem.MINE)
        assertThat(row.cells[7]).isEqualTo(BoardItem.MINE)
    }
}
