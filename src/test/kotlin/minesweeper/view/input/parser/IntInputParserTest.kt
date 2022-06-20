package minesweeper.view.input.parser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class IntInputParserTest {

    @ParameterizedTest
    @CsvSource(
        "0,10,'2'", // min, max, input
        "0,10,'0'",
        "0,10,'10'",
        "0,10,'    10   '",
        "0,10,'5'"
    )
    fun `정수 파싱 성공테스트`(minValue: Int, maxValue: Int, inputString: String) {

        // when
        val actual = IntInputParser(minValue..maxValue).parseValue(inputString)

        // then
        assertAll(
            { assertThat(actual).isInstanceOf(ParseResult.Value::class.java) },
            { assertThat((actual as? ParseResult.Value)?.value).isEqualTo(inputString.trim().toInt()) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0,10,''", // min, max, input
        "0,10,' '",
        "0,10,'abc'",
        "0,10,'-1'",
        "0,10,'11'",
        "0,10,'100'"
    )
    fun `정수 파싱 실패 테스트`(minValue: Int, maxValue: Int, inputString: String) {

        // when
        val actual = IntInputParser(minValue..maxValue).parseValue(inputString)

        // then
        assertThat(actual).isInstanceOf(ParseResult.Error::class.java)
    }

    @ParameterizedTest
    @CsvSource(
        "10,'10'",
        "10,'11'",
        "10,'100'"
    )
    fun `정수 파싱 최소값 성공 테스트`(minValue: Int, inputString: String) {

        // when
        val actual = IntInputParser(minValue = minValue).parseValue(inputString)

        // then
        assertAll(
            { assertThat(actual).isInstanceOf(ParseResult.Value::class.java) },
            { assertThat((actual as? ParseResult.Value)?.value).isEqualTo(inputString.trim().toInt()) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "10,'9'",
        "10,'-10'",
        "10,'0'"
    )
    fun `정수 파싱 최소값 실패 테스트`(minValue: Int, inputString: String) {

        // when
        val actual = IntInputParser(minValue = minValue).parseValue(inputString)

        // then
        assertThat(actual).isInstanceOf(ParseResult.Error::class.java)
    }

    @Test
    fun `정수 파싱 실패테스트 null 입력`() {

        // when
        val actual = IntInputParser().parseValue(null)

        // then
        assertThat(actual).isInstanceOf(ParseResult.Error::class.java)
    }
}
