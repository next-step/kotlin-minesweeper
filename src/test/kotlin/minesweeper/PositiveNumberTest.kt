package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import minesweeper.domain.PositiveNumber
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PositiveNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["0", "-10", "46", "100"])
    fun `너비는 1부터 30까지의 숫자만 입력 가능하다`(number: String) {
        shouldThrow<IllegalArgumentException> {
            PositiveNumber.of(number)
        }.also {
            it.message shouldBe "1부터 30까지 입력가능합니다"
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -10, 46, 100])
    fun `너비와 1부터 30까지의 숫자만 입력 가능하다`(number: Int) {
        shouldThrow<IllegalArgumentException> {
            PositiveNumber(number)
        }.also {
            it.message shouldBe "1부터 30까지 입력가능합니다"
        }
    }
}
