package minesweeper

import minesweeper.domain.Column
import minesweeper.domain.Column.Companion.COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION
import minesweeper.domain.Height
import minesweeper.domain.Position
import minesweeper.domain.Position.Companion.INPUT_NUMBER_FORMAT_EXCEPTION
import minesweeper.domain.Positions
import minesweeper.domain.Row
import minesweeper.domain.Row.Companion.ROW_ILLEGAL_ARGUMENTS_EXCEPTION
import minesweeper.domain.Width
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class PositionTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3, -4, -5])
    fun `Position에 Row를 음의 정수를 입력하였을 경우 IllegalArgumentsException을 던진다`(row: Int) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Position.of(Row.from(row), Column.from(0))
            }
            .withMessage(ROW_ILLEGAL_ARGUMENTS_EXCEPTION)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, -2, -3, -4, -5])
    fun `Position에 Column에 음의 정수를 입력하였을 경우 IllegalArgumentsException을 던진다`(column: Int) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Position.of(Row.from(0), Column.from(column))
            }
            .withMessage(COLUMN_ILLEGAL_ARGUMENTS_EXCEPTION)
    }

    @Test
    fun `String을 통해 Column과 Row가 1씩 줄어든 Position을 생성할 수 있다`() {
        val position = Position.from("1,1")

        assertThat(position).isEqualTo(Position.of(Row.from(0), Column.from(0)))
    }

    @ParameterizedTest
    @CsvSource(value = ["abc,def", "abcd,gde", "asdsadsa, eefd", "abcd"])
    fun `입력한 String이 콤마 기준으로 양옆에 Int타입이 있지 않으면 IllegalArgumentsException을 던진다`(input: String) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Position.from(input)
            }
            .withMessage(INPUT_NUMBER_FORMAT_EXCEPTION)
    }

    @ParameterizedTest
    @CsvSource(value = ["abc,def,asdf", "abcd,gde,,,,", "asdsadsa,,,, eefd", "abcd,aasdf,sdf,"])
    fun `입력한 String에 콤마가 2개이상이면 IllegalArgumentsException을 던진다`(input: String) {
        Assertions
            .assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                Position.from(input)
            }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4])
    fun `Position은 row의 시작점인지 알 수 있다`(column: Int) {
        val position = Position.of(Row.from(0), Column.from(column))

        assertThat(position.isRowStart()).isTrue
    }

    @Test
    fun `Position은 row가 같으면 column 오름차순으로 정렬된다`() {
        val positions = listOf(
            Position.of(Row.from(0), Column.from(3)),
            Position.of(Row.from(0), Column.from(2)),
            Position.of(Row.from(0), Column.from(1)),
        )

        val sortedPositions = positions.sorted()

        val expected = listOf(
            Position.of(Row.from(0), Column.from(1)),
            Position.of(Row.from(0), Column.from(2)),
            Position.of(Row.from(0), Column.from(3)),
        )

        assertThat(sortedPositions).isEqualTo(expected)
    }

    @Test
    fun `Position은 column이 같으면 row 오름차순으로 정렬된다`() {
        val positions = listOf(
            Position.of(Row.from(3), Column.from(0)),
            Position.of(Row.from(2), Column.from(0)),
            Position.of(Row.from(1), Column.from(0)),
        )

        val sortedPositions = positions.sorted()

        val expected = listOf(
            Position.of(Row.from(1), Column.from(0)),
            Position.of(Row.from(2), Column.from(0)),
            Position.of(Row.from(3), Column.from(0)),
        )

        assertThat(sortedPositions).isEqualTo(expected)
    }

    @Test
    fun `Position은 column과 row가 모두 다르면 column 우선순위가 더 높게 오름차순으로 정렬된다`() {
        val positions = listOf(
            Position.of(Row.from(1), Column.from(2)),
            Position.of(Row.from(0), Column.from(0)),
            Position.of(Row.from(2), Column.from(1)),
            Position.of(Row.from(2), Column.from(2)),
            Position.of(Row.from(1), Column.from(0)),
            Position.of(Row.from(0), Column.from(1)),
        )

        val sortedPositions = positions.sorted()

        val expected = listOf(
            Position.of(Row.from(0), Column.from(0)),
            Position.of(Row.from(1), Column.from(0)),
            Position.of(Row.from(0), Column.from(1)),
            Position.of(Row.from(2), Column.from(1)),
            Position.of(Row.from(1), Column.from(2)),
            Position.of(Row.from(2), Column.from(2)),
        )

        assertThat(sortedPositions).isEqualTo(expected)
    }

    @Test
    fun `Position의 aroundPositionList들이 인자로 들어온 PositionList과 같은 Position 갯수를 알 수 있다`() {
        val positionList = Positions.of(Width.from(3), Height.Companion.from(3)).positions

        val otherPositionList = listOf(
            Position(Row.from(1), Column.from(1)),
            Position(Row.from(0), Column.from(1)),
            Position(Row.from(1), Column.from(0)),
        )
        val position0to0ContainsOthersCount = positionList[0].countAroundPositionsContainOthers(otherPositionList)

        assertThat(position0to0ContainsOthersCount).isEqualTo(3)
    }

    @Test
    fun `Position끼리의 비교를 할 때 row가 같으면 column이 높으면 1이다`() {
        val position1 = Position.of(Row.from(2), Column.from(2))
        val position2 = Position.of(Row.from(2), Column.from(1))

        val compareValue = position1.compareTo(position2)

        assertThat(compareValue).isEqualTo(1)
    }

    @Test
    fun `Position끼리의 비교를 할 때 column이 같으면 row가 높으면 1이다`() {
        val position1 = Position.of(Row.from(2), Column.from(2))
        val position2 = Position.of(Row.from(1), Column.from(2))

        val compareValue = position1.compareTo(position2)

        assertThat(compareValue).isEqualTo(1)
    }

    @Test
    fun `Position끼리의 비교를 할 때 colum과 row가 다르면 column이 높은 것이 1이다`() {
        val position1 = Position.of(Row.from(2), Column.from(8))
        val position2 = Position.of(Row.from(1), Column.from(2))

        val compareValue = position1.compareTo(position2)

        assertThat(compareValue).isEqualTo(1)
    }

    @Test
    fun `Position은 주변 8방향 0,0 이상의 Position List들을 구할 수 있다`() {
        val position = Position.of(Row.from(0), Column.from(0))

        val aroundPositionList = position.aroundPositionList()

        val expectedPositionList = listOf(
            Position.of(Row.from(0), Column.from(1)),
            Position.of(Row.from(1), Column.from(0)),
            Position.of(Row.from(1), Column.from(1)),
        )
        assertThat(aroundPositionList).isEqualTo(expectedPositionList)
    }

    @Test
    fun `Position은 인자로 받은 PositionList들과 주변 PositionList들이 얼마나 겹치는지 알 수 있다`() {
        val position = Position.of(Row.from(1), Column.from(1))

        val otherPositionList = listOf(
            Position.of(Row.from(0), Column.from(1)),
            Position.of(Row.from(1), Column.from(0)),
            Position.of(Row.from(1), Column.from(2)),
            Position.of(Row.from(2), Column.from(2)),
            Position.of(Row.from(10), Column.from(10)),
        )

        val countAroundPositionContainsOthers = position.countAroundPositionsContainOthers(otherPositionList)

        assertThat(countAroundPositionContainsOthers).isEqualTo(4)
    }
}
