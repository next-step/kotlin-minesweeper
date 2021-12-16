package minesweeper

import minesweeper.domain.Column
import minesweeper.domain.Column.Companion.COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION
import minesweeper.domain.Position
import minesweeper.domain.Row
import minesweeper.domain.Row.Companion.ROW_ILLEGAL_ARGUMENTS_EXCEPTION
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PositionTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3, -4, -5])
    fun `Position에 Row를 음의 정수를 입력하였을 경우 IllegalArgumentsException을 던진다`(row: Int) {
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
    fun `Position에 Column에 음의 정수를 입력하였을 경우 IllegalArgumentsException을 던진다`(column: Int) {
        val columnInstance = Column.from(column)

        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Position.from(Row.from(8), columnInstance)
            }
            .withMessage(COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4])
    fun `Position은 row의 시작점인지 알 수 있다`(column: Int) {
        val position = Position.from(Row.from(0), Column.from(column))

        assertThat(position.isRowStart()).isTrue
    }

    @Test
    fun `Position은 row가 같으면 column 오름차순으로 정렬된다`() {
        val positions = listOf(
            Position.from(Row.from(0), Column.from(3)),
            Position.from(Row.from(0), Column.from(2)),
            Position.from(Row.from(0), Column.from(1)),
        )

        val sortedPositions = positions.sorted()

        val expected = listOf(
            Position.from(Row.from(0), Column.from(1)),
            Position.from(Row.from(0), Column.from(2)),
            Position.from(Row.from(0), Column.from(3)),
        )

        assertThat(sortedPositions).isEqualTo(expected)
    }

    @Test
    fun `Position은 column이 같으면 row 오름차순으로 정렬된다`() {
        val positions = listOf(
            Position.from(Row.from(3), Column.from(0)),
            Position.from(Row.from(2), Column.from(0)),
            Position.from(Row.from(1), Column.from(0)),
        )

        val sortedPositions = positions.sorted()

        val expected = listOf(
            Position.from(Row.from(1), Column.from(0)),
            Position.from(Row.from(2), Column.from(0)),
            Position.from(Row.from(3), Column.from(0)),
        )

        assertThat(sortedPositions).isEqualTo(expected)
    }

    @Test
    fun `Position은 column과 row가 모두 다르면 column 우선순위가 더 높게 오름차순으로 정렬된다`() {
        val positions = listOf(
            Position.from(Row.from(1), Column.from(2)),
            Position.from(Row.from(0), Column.from(0)),
            Position.from(Row.from(2), Column.from(1)),
            Position.from(Row.from(2), Column.from(2)),
            Position.from(Row.from(1), Column.from(0)),
            Position.from(Row.from(0), Column.from(1)),
        )

        val sortedPositions = positions.sorted()

        val expected = listOf(
            Position.from(Row.from(0), Column.from(0)),
            Position.from(Row.from(1), Column.from(0)),
            Position.from(Row.from(0), Column.from(1)),
            Position.from(Row.from(2), Column.from(1)),
            Position.from(Row.from(1), Column.from(2)),
            Position.from(Row.from(2), Column.from(2)),
        )

        assertThat(sortedPositions).isEqualTo(expected)
    }
}
