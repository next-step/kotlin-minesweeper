package domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GameBoardTest {

    @Test
    @DisplayName("게임 보드는 주어진 높이와 너비를 가진다")
    fun `게임 보드는 주어진 높이와 너비를 가진다`() {
        val height = 10
        val width = 10
        val board = GameBoard(height, width)

        assertEquals(height, board.height)
        assertEquals(width, board.width)
    }
}
