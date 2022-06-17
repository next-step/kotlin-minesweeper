import domain.BoardItem
import domain.BoardRow
import domain.BoardRows
import domain.MinePosition
import domain.Mines
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardRowsTest {
    @Test
    fun `지뢰의 위치를 지정하면 Mine이 나온다`() {
        val mines = Mines(
            listOf(
                MinePosition(0, 3),
                MinePosition(2, 7)
            ).toSet()
        )

        val row0 = BoardRow(0, 10, mines)
        val row1 = BoardRow(1, 10, mines)
        val row2 = BoardRow(2, 10, mines)

        val rows = BoardRows(
            listOf(row0, row1, row2)
        )

        assertThat(rows[0].cells[3] is BoardItem.Mine).isTrue
        assertThat(rows[2].cells[7] is BoardItem.Mine).isTrue
    }
}
