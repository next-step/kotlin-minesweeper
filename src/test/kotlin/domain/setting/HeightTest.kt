package domain.setting

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class HeightTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 501])
    fun `높이는 500 이하인 양의 정수이다`(value: Int) {
        assertThrows<IllegalArgumentException> { Height(value) }
    }
}
