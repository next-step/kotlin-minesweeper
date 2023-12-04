package domain.setting

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WidthTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 501])
    fun `너비는 500 이하인 양의 정수이다`(value: Int) {
        assertThrows<IllegalArgumentException> { Width(value) }
    }
}
