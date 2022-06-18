package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellStateTest {

    @Test
    fun `마인 셀의 상태에 주변 지뢰 갯수는 0 으로 세팅한다`() {
        val allBoardPosition = FixtureMineSweeper.positions10x10
        val position = Position(0, 0).also { it.setNearPositions(allBoardPosition) }
        val minePosition = Position(8, 8)
        val minePositions = CellState.of(position, Positions(listOf(minePosition)))

        assertThat(minePositions.mineCount).isEqualTo(0)
    }

    @Test
    fun `논 마인 셀의 상태에 주변 지뢰 갯수만큼(1개) 세팅한다`() {
        val allBoardPosition = FixtureMineSweeper.positions2x2
        val position = Position(0, 0).also { it.setNearPositions(allBoardPosition) }
        val minePosition = Position(1, 1)
        val minePositions = CellState.of(position, Positions(listOf(minePosition)))

        assertThat(minePositions.mineCount).isEqualTo(1)
    }
}
