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
        assertThrows<IllegalArgumentException>(ErrorCode.NOT_POSITIVE_NUMBER_ERROR.msg) {
            PositiveNumber(value)
        }
    }
}
