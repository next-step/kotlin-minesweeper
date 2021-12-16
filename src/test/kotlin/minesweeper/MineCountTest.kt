package minesweeper

import minesweeper.domain.MineCount
import minesweeper.domain.MineCount.Companion.MINE_COUNT_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE
import minesweeper.domain.MineCount.Companion.MINE_COUNT_NUMBER_FORMAT_EXCEPTION_MESSAGE
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class MineCountTest {

    @ParameterizedTest
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4"])
    fun `String을 입력받아서 MineCount를 만들 수 있다`(input: String, expected: Int) {
        Assertions.assertThat(MineCount.from(input)).isEqualTo(MineCount(expected))
    }

    @ParameterizedTest
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4"])
    fun `Int를 입력받아서 MineCount를 만들 수 있다`(input: Int, expected: Int) {
        Assertions.assertThat(MineCount.from(input)).isEqualTo(MineCount(expected))
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "-2", "-4", "-5"])
    fun `MineCount에 음의정수를 입력했을 때 IllegalArgumentsException을 던진다`(input: String) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                MineCount.from(input)
            }
            .withMessage(MINE_COUNT_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE)
    }

    @ParameterizedTest
    @ValueSource(strings = ["abc", "def", "@!#", "asmkd", "bmcvo"])
    fun `MineCount에 Int가 아닌 수를 입력할 경우 NumberFormatException을 던진다`(input: String) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                MineCount.from(input)
            }
            .withMessage(MINE_COUNT_NUMBER_FORMAT_EXCEPTION_MESSAGE)
    }
}
