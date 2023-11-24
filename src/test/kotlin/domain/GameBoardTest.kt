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

    @Test
    @DisplayName("게임 보드는 지정된 수의 지뢰를 랜덤하게 배치한다.")
    fun `게임 보드는 지정된 수의 지뢰를 랜덤하게 배치한다`() {
        val height = 10
        val width = 10
        val mineCount = 10
        val board = GameBoard(height, width)
        board.placeMines(mineCount)

        assertEquals(mineCount, board.countMines())
    }
}
