package minesweeper.domain.position

import minesweeper.domain.board.BoardSize
import minesweeper.domain.cell.Cells
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {
    @ParameterizedTest
    @CsvSource("1,1,3", "4,4,8", "1,9,5")
    fun `각 포지션에 대해서는 인접한 포지션들이 있다`(x: Int, y: Int, count: Int) {
        // given
        val position = Position.of(x, y)
        position.updateAdjacentPositions(Positions.of(BoardSize.of(10, 10)))

        // when
        val adjacentPositionsCount = position.adjacentPositions.size

        // then
        assertThat(adjacentPositionsCount).isEqualTo(count)
    }

    @ParameterizedTest
    @CsvSource("1,4,1", "2,1,2", "2,2,3", "4,4,0")
    fun `포지션에 대해 인접한 지뢰 갯수를 구한다`(x: Int, y: Int, count: Int) {
        // given
        val position = Position.of(x, y)
        position.updateAdjacentPositions(Positions.of(BoardSize.of(10, 10)))
        val minePositions = Cells.of(Positions(listOf(Position.of(1, 1), Position.of(1, 2), Position.of(1, 3))))

        // when
        val adjacentMinesCount = position.countingAdjacentMines(minePositions)

        // then
        assertThat(adjacentMinesCount).isEqualTo(count)
    }
}
