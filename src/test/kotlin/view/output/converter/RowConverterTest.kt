package view.output.converter

import domain.Cell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RowConverterTest {
    @Test
    fun `RowConverter는 Row를 출력을 위한 문자열로 변환한다`() {
        val row = mutableListOf(Cell.Land.ONE, Cell.Mine, Cell.Land.TWO, Cell.Mine, Cell.Land.ONE, Cell.Land.ZERO)

        assertThat(RowConverter.convert(row)).isEqualTo("1 * 2 * 1 0")
    }
}
