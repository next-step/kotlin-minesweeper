package minesweeper.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MineCountTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, -100, -150])
    fun `지뢰 개수는 항상 0 이상의 값을 가진다`(count: Int) {
        assertThrows<RuntimeException> { MineCount(count) }
    }
}
