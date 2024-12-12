package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WidthTest {
    @ParameterizedTest
    @ValueSource(ints = [ 0, -1])
    fun `주 생성자에 양수가 아닌 값이 전달되었을 때를 테스트`(value: Int) {
        shouldThrow<IllegalArgumentException> { Width(value) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["a", " "])
    fun `부 생성자에 숫자가 아닌 값이 전달되었을 때를 테스트`(value: String) {
        shouldThrow<IllegalArgumentException> { Width(value) }
    }
}
