package minesweeper.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WidthTest {

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -100, -150])
    fun `너비는 항상 1 이상의 값을 가진다`(width: Int) {
        assertThrows<RuntimeException> { Width(width) }
    }
}
