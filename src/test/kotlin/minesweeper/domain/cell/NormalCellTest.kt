package minesweeper.domain.cell

import minesweeper.domain.board.BoardSize
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NormalCellTest {

    @Test
    fun `일반 셀은 인접한 셀이 있으면 true 를 반환한다`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))

        val normalCell = NormalCell(positions[0])

        // when
        val containsAdjacentPositions = normalCell.isContainsAdjacentPositions(Position.of(1, 2))

        // then
        assertThat(containsAdjacentPositions).isTrue
    }

    @Test
    fun `일반 셀은 주위에 지뢰가 있으면 인접한 지뢰 수를 가지고 있다`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val normalCell = NormalCell(positions[3])
        val mineCell = mineCell(listOf(Position.of(1, 1), Position.of(1, 2), Position.of(1, 3)), positions)

        // when
        normalCell.countingAdjacentMines(mineCell)

        // then
        assertThat(normalCell.getCellAdjacentCount()).isEqualTo(1)
    }
}
