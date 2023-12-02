package minesweeper.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class WidthTest {
    @Test
    fun `너비에 1 미만의 숫자가 들어가면 IllegalArgumentException 발생한다`() {
        assertThrows<IllegalArgumentException> { Width(0) }
    }
}
