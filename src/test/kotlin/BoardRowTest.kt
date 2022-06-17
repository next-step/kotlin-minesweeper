import domain.BoardItem
import domain.BoardRow
import domain.MinePosition
import domain.Mines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardRowTest {
    @Test
    fun `Row에서 지뢰의 위치를 지정하면 Mine이 나온다`() {
        val mines = Mines(
            listOf(
                MinePosition(0, 3),
                MinePosition(0, 7)
            ).toSet()
        )

        val row = BoardRow(0, 10, mines)
        assertThat(row.cells[3] is BoardItem.Mine).isTrue
        assertThat(row.cells[7] is BoardItem.Mine).isTrue
    }
}
