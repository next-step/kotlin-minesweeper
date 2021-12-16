package minesweeper

import minesweeper.domain.Column
import minesweeper.domain.Column.Companion.COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ColumnTest {

    @ParameterizedTest
    @CsvSource(value = ["1, 1", "2, 2", "3, 3", "4, 4"])
    fun `Int를 입력받아서 Column을 만들 수 있다`(input: Int, expected: Int) {
        Assertions.assertThat(Column.from(input)).isEqualTo(Column(expected))
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3, -4, -5])
    fun `Column에 음의 정수를 입력했을 때 IllegalArgumentsException을 던진다`(input: Int) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Column.from(input)
            }
            .withMessage(COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION)
    }
}
