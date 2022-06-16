package minesweeper.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class GameBoardSizeTest {

    @Test
    fun `게임보드 사이즈 validation fail 세로 0`() {
        assertThrows<IllegalArgumentException> {
            GameBoardSize(5, 0)
        }
    }

    @Test
    fun `게임보드 사이즈 validation fail 가로 0`() {
        assertThrows<IllegalArgumentException> {
            GameBoardSize(0, 5)
        }
    }
}
