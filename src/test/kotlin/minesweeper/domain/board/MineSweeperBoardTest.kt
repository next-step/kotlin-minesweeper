package minesweeper.domain.board

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import minesweeper.domain.position.Position
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

    @Test
    fun `NxM 크기의 보드에서 (x, y) 위치가 포함됐는지 확인할 때 범위를 벗어나면 false 리턴`() {
        val mineQty = 1
        val boardRange = BoardRange(height = 10, width = 10)

        val board = MineSweeperBoard(boardRange = boardRange, mineQuantity = mineQty)

        board.containsPosition(Position(x = 1000, y = 1000)) shouldBe false
    }

    @Test
    fun `NxM 크기의 보드에서 (x, y) 위치가 포함됐는지 확인할 때 범위에 포함되면 true 리턴`() {
        val mineQty = 1
        val boardRange = BoardRange(height = 10, width = 10)

        val board = MineSweeperBoard(boardRange = boardRange, mineQuantity = mineQty)

        board.containsPosition(Position(x = 1, y = 1)) shouldBe true
    }
}
