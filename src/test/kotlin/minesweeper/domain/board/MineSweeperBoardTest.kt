package minesweeper.domain.board

import io.kotest.assertions.throwables.shouldThrow
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import org.junit.jupiter.api.Test

class MineSweeperBoardTest {

    @Test
    fun `지뢰 개수가 보드 크기를 초과하면 예외가 발생한다`() {
        val minePositions = Positions((1..101).map { Position(x = it, y = it) })
        val boardRange = BoardRange(height = 10, width = 10)

        shouldThrow<IllegalArgumentException> {
            MineSweeperBoardGenerator.create(boardRange, minePositions)
        }
    }
}
