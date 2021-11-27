package minesweeper.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class HeightTest {

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -100, -150])
    fun `높이는 항상 1 이상의 값을 가진다`(height: Int) {
        assertThrows<RuntimeException> { Height(height) }
    }
}
