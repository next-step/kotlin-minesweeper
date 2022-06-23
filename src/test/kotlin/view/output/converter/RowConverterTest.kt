package view.output.converter

import domain.Cell
import domain.Row
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RowConverterTest {
    @Test
    fun `RowConverter는 Row를 출력을 위한 문자열로 변환한다`() {
        val cells = listOf(Cell.LAND, Cell.MINE, Cell.LAND, Cell.MINE, Cell.LAND, Cell.LAND)
        val row = Row(cells)

        assertThat(RowConverter.convert(row)).isEqualTo("C * C * C C")
    }
}
