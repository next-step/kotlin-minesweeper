package minesweeper

import minesweeper.domain.Column
import minesweeper.domain.Column.Companion.COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION
import minesweeper.domain.Position
import minesweeper.domain.Row
import minesweeper.domain.Row.Companion.ROW_ILLEGAL_ARGUMENTS_EXCEPTION
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PositionTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3, -4, -5])
    fun `Position에 Row를 0미만을 입력하였을 경우 IllegalArgumentsException을 던진다`(row: Int) {
        val rowInstance = Row.from(row)

        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Position.from(rowInstance, Column.from(8))
            }
            .withMessage(ROW_ILLEGAL_ARGUMENTS_EXCEPTION)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3, -4, -5])
    fun `Position에 Column에 0미만을 입력하였을 경우 IllegalArgumentsException을 던진다`(column: Int) {
        val columnInstance = Column.from(column)

        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Position.from(Row.from(8), columnInstance)
            }
            .withMessage(COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION)
    }
}
