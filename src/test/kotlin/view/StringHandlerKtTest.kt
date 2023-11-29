package view

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StringHandlerKtTest {
    @ParameterizedTest
    @ValueSource(strings = ["", " "])
    fun `정수 변환 메서드에 빈값 전달시 예외를 던진다`(input: String) {
        assertThatThrownBy { inputToInt(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("빈 값이 입력되었습니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["3.14", "15$", "1.7E+3"])
    fun `정수 변환 메서드에 전달된 문자열이 숫자 외의 문자를 포함할 경우 예외를 던진다`(input: String) {
        assertThatThrownBy { inputToInt(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("숫자만 입력되어야 합니다.")
    }
}
