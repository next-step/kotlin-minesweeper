package minesweeper.domain.board

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class MineSweeperBoardTest {

    @Test
    fun `지뢰 개수가 보드 크기를 초과하면 예외가 발생한다`() {
        val mineQty = 101
        val boardRange = BoardRange(height = 10, width = 10)

        shouldThrow<IllegalArgumentException> {
            MineSweeperBoard(boardRange, mineQty)
        }
    }
}
