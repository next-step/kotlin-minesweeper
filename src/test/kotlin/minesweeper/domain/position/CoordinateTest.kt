package minesweeper.domain.position

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class CoordinateTest {

    @Test
    fun `coordinate 는 0보다 커야한다 1인 경우 생성 가능`() {
        assertDoesNotThrow { Coordinate.of(1) }
    }

    @Test
    fun `coordinate 는 0보다 커야한다 0인 경우 생성 불가능`() {
        assertThrows<IllegalArgumentException> { Coordinate.of(0) }
    }
}
