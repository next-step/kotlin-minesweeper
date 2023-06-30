package minesweeper

import domain.PositiveNumber
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PositiveNumberTest {
    @Test
    fun `양수의 값을 가진다`() {
        val injectValue = 3

        val positiveNumber = PositiveNumber(injectValue)

        positiveNumber.value shouldBe injectValue
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `음수 혹은 0을 가지면 에러를 반환한다`(value: Int) {
        assertThrows<RuntimeException>(ErrorCode.NOT_POSITIVE_NUMBER_ERROR.msg) {
            PositiveNumber(value)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["+", "@", "-", "양수", "음수"])
    fun `숫자 이외의 값을 가지면 에러를 반환한다`(value: Any) {
        assertThrows<RuntimeException>(ErrorCode.NOT_POSITIVE_NUMBER_ERROR.msg) {
            PositiveNumber(value as Int)
        }
    }
}
