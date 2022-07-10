package view.output.converter

import domain.Cell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class LandConverterTest {
    @ParameterizedTest
    @EnumSource(Cell.Land::class)
    fun `LandConverter는 Land를 출력을 위한 문자열로 변환한다`(input: Cell.Land) {
        val expected = when (input) {
            Cell.Land.ZERO -> "0"
            Cell.Land.ONE -> "1"
            Cell.Land.TWO -> "2"
            Cell.Land.THREE -> "3"
            Cell.Land.FOUR -> "4"
            Cell.Land.FIVE -> "5"
            Cell.Land.SIX -> "6"
            Cell.Land.SEVEN -> "7"
            Cell.Land.EIGHT -> "8"
        }

        assertThat(LandConverter.convert(input)).isEqualTo(expected)
    }
}
