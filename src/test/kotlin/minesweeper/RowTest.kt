package minesweeper

import minesweeper.domain.Row
import minesweeper.domain.Row.Companion.ROW_ILLEGAL_ARGUMENTS_EXCEPTION
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class RowTest {

    @ParameterizedTest
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4"])
    fun `Int를 입력받아서 Row을 만들 수 있다`(input: Int, expected: Int) {
        Assertions.assertThat(Row.from(input)).isEqualTo(Row(expected))
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3, -4, -5])
    fun `Row에 음의 정수를 입력했을 때 IllegalArgumentsException을 던진다`(input: Int) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Row.from(input)
            }
            .withMessage(ROW_ILLEGAL_ARGUMENTS_EXCEPTION)
    }

    @Test
    fun `0은 Row의 시작점이다`() {
        val row = Row.from(0)

        Assertions.assertThat(row.isStart).isTrue
    }
}
