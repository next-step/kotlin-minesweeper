package minesweeper

import minesweeper.domain.Width
import minesweeper.domain.Width.Companion.WIDTH_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE
import minesweeper.domain.Width.Companion.WIDTH_NUMBER_FORMAT_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class WidthTest {

    @ParameterizedTest
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4"])
    fun `String을 입력받아서 Width를 만들 수 있다`(input: String, expected: Int) {
        assertThat(Width.from(input)).isEqualTo(Width(expected))
    }

    @ParameterizedTest
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4"])
    fun `Int를 입력받아서 Width를 만들 수 있다`(input: Int, expected: Int) {
        assertThat(Width.from(input)).isEqualTo(Width(expected))
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "-1", "-2", "-4", "-5"])
    fun `Width에 1미만을 입력했을 때 IllegalArgumentsException을 던진다`(input: String) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Width.from(input)
            }
            .withMessage(WIDTH_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE)
    }

    @ParameterizedTest
    @ValueSource(strings = ["abc", "def", "@!#", "asmkd", "bmcvo"])
    fun `Width에 Int가 아닌 수를 입력할 경우 NumberFormatException을 던진다`(input: String) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Width.from(input)
            }
            .withMessage(WIDTH_NUMBER_FORMAT_EXCEPTION_MESSAGE)
    }
}
