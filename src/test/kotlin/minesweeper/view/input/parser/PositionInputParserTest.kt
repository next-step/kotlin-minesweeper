package minesweeper.view.input.parser

import minesweeper.model.coordinate.BoardArea
import minesweeper.model.coordinate.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PositionInputParserTest {

    @ParameterizedTest
    @CsvSource(
        "10,5,'10,10'",
        "10,10,'1,10'",
        "10,10,'1,1,2'",
        "10,5,'10,1'",
        "10,5,'10'",
        "10,5,'a'",
        "10,5,'a,a,a'",
    )
    fun `Position 파싱 에러 테스트`(rowCount: Int, columnCount: Int, inputString: String) {

        // when
        val actual = PositionInputParser(area = BoardArea.of(rowCount, columnCount))
            .parseValue(inputString)

        // then
        assertThat(actual).isInstanceOf(ParseResult.Error::class.java)
    }

    @ParameterizedTest
    @CsvSource(
        "10,5,'0,0'",
        "10,10,'9,9'",
        "10,10,'5,5'",
        "10,10,'  5,   5 '"

    )
    fun `Position 파싱 성공 테스트`(
        rowCount: Int,
        columnCount: Int,
        inputString: String
    ) {

        // when
        val actual = PositionInputParser(area = BoardArea.of(rowCount, columnCount))
            .parseValue(inputString)

        val parsedValue = inputString.split(",")
            .map { it.trim().toInt() }

        val expected = Position(parsedValue[0], parsedValue[1])

        // then
        assertAll(
            { assertThat(actual).isInstanceOf(ParseResult.Value::class.java) },
            {
                assertThat((actual as ParseResult.Value<Position>).value)
                    .isEqualTo(expected)
            }
        )
    }
}
