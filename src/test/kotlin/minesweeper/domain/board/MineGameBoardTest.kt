package minesweeper.domain.board

import io.kotest.assertions.throwables.shouldThrow
import minesweeper.baord.domain.GameBoardRange
import minesweeper.baord.domain.MineGameBoard
import org.junit.jupiter.api.Test

class MineGameBoardTest {

    @Test
    fun `게임 보드의 지뢰 개수가 보드의 높이 * 너비보다 크다면 IllegalArgumentException 이 발생한다`() {
        val height = 10
        val width = 10
        val mineQuantity = 101
        val boardRange = GameBoardRange(height = height, width = width)

        shouldThrow<IllegalArgumentException> {
            MineGameBoard(boardRange, mineQuantity)
        }
    }
}
