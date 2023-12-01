package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameBoardTest {
    @Test
    fun `자연수로 높이와 너비를 입력하면 게임보드를 만든다`() {
        val height = 3
        val width = 4
        val gameBoard = GameBoard(height, width)

        height shouldBe gameBoard.height
        width shouldBe gameBoard.width
    }

    @Test
    fun `지뢰찾기 보드의 높이와 너비는 자연수여야 한다`() {
        assertThrows<IllegalArgumentException> {
            GameBoard(0, 5)
        }

        assertThrows<IllegalArgumentException> {
            GameBoard(3, -1)
        }
    }
}
