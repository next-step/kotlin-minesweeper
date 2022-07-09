package domain

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PositiveNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, -25])
    internal fun `PositiveNumber 타입은 0 미만의 숫자일 경우 IllegalArgumentException을 반환한다`(testValue: Int) {
        assertThrows<IllegalArgumentException> {
            PositiveNumber(testValue)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    internal fun `PositiveNumber 타입은 자연수여야 한다`(testValue: Int) {
        assertDoesNotThrow {
            PositiveNumber(testValue)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "a", "0", "-1"])
    internal fun `정수나 0이 아닌 문자열일 경우 IllegalArgumentException을 반환한다`(testValue: String) {
        assertThrows<IllegalArgumentException> {
            PositiveNumber(testValue)
        }
    }
}
