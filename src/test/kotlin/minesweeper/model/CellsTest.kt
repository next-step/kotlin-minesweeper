package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {

    @Test
    fun `Cell 중에 가장 Column이 큰 값을 확인할 수 있다`() {
        val cells = Cells(1 to 1, 1 to 2, 1 to 3, 2 to 1, 2 to 2, 2 to 3)
        val actual = cells.maxColumn()
        assertThat(actual).isEqualTo(Column(3))
    }

    @Test
    fun `Cell 중에 가장 Row가 큰 값을 확인할 수 있다`() {
        val cells = Cells(1 to 1, 1 to 2, 1 to 3, 2 to 1, 2 to 2, 2 to 3)
        val actual = cells.maxRow()
        assertThat(actual).isEqualTo(Row(2))
    }

    @Test
    fun `Row, Column에 해당하는 Cell을 확인할 수 있다`() {
        val cells = Cells(1 to 1, 1 to 2, 1 to 3, 2 to 1, 2 to 2, 2 to 3)
        assertAll(
            { assertThat(cells.get(Row(1), Column(1))).isNotNull },
            { assertThat(cells.get(Row(1), Column(10))).isNull() },
        )
    }
}
