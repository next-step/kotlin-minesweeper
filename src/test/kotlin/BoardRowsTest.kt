import domain.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardRowsTest {
    @Test
    fun `넣어줬던 지뢰의 위치가 있는 곳을 꺼냈을때는 MINE 타입이 나와야함`() {
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

        assertThat(rows[0].cells[3]).isEqualTo(BoardItem.MINE)
        assertThat(rows[2].cells[7]).isEqualTo(BoardItem.MINE)
    }
}
