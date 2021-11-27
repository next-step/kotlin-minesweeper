package domain

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class WidthTest {
    @ValueSource(ints = [-1, 0])
    @ParameterizedTest
    fun `value 의 값이 0 이하라면 에러`(value: Int) {
        org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            Height(value)
        }
    }
}
