package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameBoardTest {
    @Test
    internal fun `게임보드 생성 테스트`() {
        val gameBoardSize = GameBoardSize(5, 5)
        val positions = Positions.of(gameBoardSize)
        val minePositions = positions.createRandomMinePosition(5)
        val cells = Cells.of(positions.positions, minePositions)

        val gameBoard = GameBoard(cells)

        assertThat(gameBoard.cells.cells.size).isEqualTo(25)
    }
}