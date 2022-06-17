package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellsTest {
    @Test
    fun `게임판(5*5)에 꽂을 셀과 지뢰 5개를 생성한다`() {
        val positions = Positions(GameBoardSize(5, 5).createPositions())
        val minePositions = positions.createRandomMinePosition(5)
        val cells = Cells.of(positions.positions, minePositions)

        assertThat(cells.cells.size).isEqualTo(25)
        assertThat(cells.cells.count { it.cellType == CellType.MINE }).isEqualTo(5)
        assertThat(cells.cells.count { it.cellType == CellType.NON_MINE }).isEqualTo(20)
    }
}
