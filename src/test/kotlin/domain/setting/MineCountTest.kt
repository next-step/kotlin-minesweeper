package domain.setting

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MineCountTest {
    @ParameterizedTest
    @ValueSource(ints = [-1, 0])
    fun `지뢰 개수는 양의 정수이다`(value: Int) {
        assertThrows<IllegalArgumentException> { MineCount(value) }
    }
}
