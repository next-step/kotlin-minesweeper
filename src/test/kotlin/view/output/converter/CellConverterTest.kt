package view.output.converter

import domain.Cell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellConverterTest {
    @Test
    fun `CellConverter는 Cell을 출력을 위한 문자열로 변환한다`() {
        val mine = Cell.Mine
        val land = Cell.Land.ZERO

        assertAll(
            { assertThat(CellConverter.convert(mine)).isEqualTo("*") },
            { assertThat(CellConverter.convert(land)).isEqualTo("0") }
        )
    }
}
