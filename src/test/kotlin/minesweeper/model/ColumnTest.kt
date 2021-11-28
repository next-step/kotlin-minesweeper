package minesweeper.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ColumnTest {

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -100, -150])
    fun `열(Column)는 항상 1 이상의 값을 가진다`(value: Int) {
        assertThrows<RuntimeException> { Column(value) }
    }
}
