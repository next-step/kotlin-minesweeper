package minesweeper.domain.cell

import minesweeper.domain.position.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineCellTest {

    @Test
    fun `지뢰는 인접한 셀을 false 로 반환한다`() {
        // given
        val mineCell = MineCell(Position.of(1, 1))

        // when
        val containsAdjacentPositions = mineCell.isContainsAdjacentPositions(Position.of(1, 2))

        // then
        assertThat(containsAdjacentPositions).isFalse
    }

    @Test
    fun `지뢰의 값은 -1 이다`() {
        // given
        val mineCell = MineCell(Position.of(1, 1))

        // when
        val mineValue = mineCell.getCellAdjacentCount()

        // then
        assertThat(mineValue).isEqualTo(-1)
    }
}
