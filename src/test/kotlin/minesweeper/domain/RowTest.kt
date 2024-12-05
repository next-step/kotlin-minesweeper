package minesweeper.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class RowTest {
    @Test
    fun `지뢰찾기 보드의 한 행을 생성한다`() {
        assertDoesNotThrow { Row(3) }
    }

    @ValueSource(ints = [0, -1])
    @ParameterizedTest
    fun `지뢰찾기 보드의 한 행을 생성할 때 0보다 작은 값이 들어오면 예외가 발생한다`(col: Int) {
        assertThrows<IllegalArgumentException> { Row(col) }
    }
}
