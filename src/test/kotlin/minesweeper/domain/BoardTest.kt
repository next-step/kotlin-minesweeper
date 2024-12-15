package minesweeper.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class BoardTest {
    @Test
    fun `빈 판을 생성할 수 없다`() {
        assertThrows<IllegalArgumentException> { Board(emptyMap()) }
    }
}
