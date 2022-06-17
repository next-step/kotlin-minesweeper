package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellStateTest {

    @Test
    fun `마인 셀의 상태에 주변 지뢰 갯수는 0 으로 세팅한다`() {
        val allBoardPosition = allBoardPositions
        val position = Position(0, 0).also { it.setNearPositions(allBoardPosition) }
        val minePosition = Position(1, 1)
        val minePositions = CellState.of(position, Positions(listOf(minePosition)), CellType.MINE)

        assertThat(minePositions.mineCount).isEqualTo(0)
    }

    @Test
    fun `논 마인 셀의 상태에 주변 지뢰 갯수만큼(1개) 세팅한다`() {
        val allBoardPosition = allBoardPositions
        val position = Position(0, 0).also { it.setNearPositions(allBoardPosition) }
        val minePosition = Position(1, 1)
        val minePositions = CellState.of(position, Positions(listOf(minePosition)), CellType.NON_MINE)

        assertThat(minePositions.mineCount).isEqualTo(1)
    }

    private val allBoardPositions = Positions(
        listOf(
            Position(0, 0),
            Position(0, 1),
            Position(1, 0),
            Position(1, 1)
        )
    )
}
