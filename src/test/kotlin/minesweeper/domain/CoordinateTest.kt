package minesweeper.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class CoordinateTest {
    @Test
    fun `y축 좌표는 0 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { Coordinate(-1, 1) }
    }

    @Test
    fun `x축 좌표는 0 이상이어야 한다`() {
        assertThrows<IllegalArgumentException> { Coordinate(1, -1) }
    }
}
