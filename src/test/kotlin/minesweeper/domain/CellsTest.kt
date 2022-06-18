package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellsTest {
    @Test
    fun `게임판(10*10)에 꽂을 셀과 지뢰 5개를 생성한다`() {
        val positions = Positions(FixtureMineSweeper.positions10x10)
        val minePositions = positions.createRandomMinePosition(5)
        val cells = Cells.of(positions, minePositions)

        assertThat(cells.cells.size).isEqualTo(100)
        assertThat(cells.cells.count { it.cellState.cellType == CellType.MINE }).isEqualTo(5)
        assertThat(cells.cells.count { it.cellState.cellType == CellType.NON_MINE }).isEqualTo(95)
    }
}
