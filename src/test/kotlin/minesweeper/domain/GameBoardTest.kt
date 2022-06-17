package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameBoardTest {
    @Test
    internal fun `게임보드 생성 테스트`() {
        val positions = Positions(GameBoardSize(5, 5).createPositions())
        val minePositions = positions.createRandomMinePosition(5)
        val cells = Cells.of(positions, minePositions)

        val gameBoard = GameBoard(cells)

        assertThat(gameBoard.cells.cells.size).isEqualTo(25)
    }
}
