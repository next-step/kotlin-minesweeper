package minesweeper

import minesweeper.domain.Height
import minesweeper.domain.Height.Companion.HEIGHT_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE
import minesweeper.domain.Height.Companion.HEIGHT_NUMBER_FORMAT_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class HeightTest {

    @ParameterizedTest
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4"])
    fun `String을 입력받아서 Height 만들 수 있다`(input: String, expected: Int) {
        Assertions.assertThat(Height.from(input)).isEqualTo(Height(expected))
    }

    @ParameterizedTest
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4"])
    fun `Int를 입력받아서 Height을 만들 수 있다`(input: Int, expected: Int) {
        Assertions.assertThat(Height.from(input)).isEqualTo(Height(expected))
    }

    @ParameterizedTest
    @ValueSource(strings = ["0", "-1", "-2", "-4", "-5"])
    fun `Height에 1미만을 입력했을 때 IllegalArgumentsException을 던진다`(input: String) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Height.from(input)
            }
            .withMessage(HEIGHT_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE)
    }

    @ParameterizedTest
    @ValueSource(strings = ["abc", "def", "@!#", "asmkd", "bmcvo"])
    fun `Height에 Int가 아닌 수를 입력할 경우 NumberFormatException을 던진다`(input: String) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Height.from(input)
            }
            .withMessage(HEIGHT_NUMBER_FORMAT_EXCEPTION_MESSAGE)
    }
}
