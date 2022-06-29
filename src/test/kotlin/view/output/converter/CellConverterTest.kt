package view.output.converter

import domain.Cell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CellConverterTest {
    @ParameterizedTest
    @EnumSource(Cell::class)
    fun `CellConverter는 Cell을 출력을 위한 문자열로 변환한다`(input: Cell) {
        val expected = if (input == Cell.LAND) "C" else "*"

        assertThat(CellConverter.convert(input)).isEqualTo(expected)
    }
}
