package view.input.converter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullSource
import org.junit.jupiter.params.provider.ValueSource

class StringToIntConverterTest {
    @ParameterizedTest
    @ValueSource(strings = ["1", "100", "1003"])
    fun `StringToIntConverter는 String 타입으로 들어온 input을 지정한 타입으로 변환하여 반환한다`(input: String?) {
        assertThat(StringToIntConverter.convert(input)).isEqualTo(input?.toInt())
    }

    @ParameterizedTest
    @NullSource
    fun `null이 들어올 경우 0을 반환한다`(input: String?) {
        assertThat(StringToIntConverter.convert(input)).isZero
    }

    @ParameterizedTest
    @ValueSource(strings = ["ㅁㄴㄹ1", "1!!!!0", "ㅇㄴㅎㅇㄴㅎ1003", ""])
    fun `int로 변환할 수 없는 값이 들어올 경우 0을 반환한다`(input: String?) {
        assertThat(StringToIntConverter.convert(input)).isZero
    }

    @ParameterizedTest
    @ValueSource(strings = ["-1", "-100", "-1003"])
    fun `음수가 들어올 경우 0을 반환한다`(input: String?) {
        assertThat(StringToIntConverter.convert(input)).isZero
    }
}
