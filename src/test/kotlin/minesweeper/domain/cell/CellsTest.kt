package minesweeper.domain.cell

import minesweeper.domain.board.BoardSize
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CellsTest {

    @Test
    fun `100개의 셀에 3개의 지뢰를 넣었을 경우 위치가 정확한지 테스트`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position.of(1, 1), Position.of(1, 2), Position.of(1, 3)))
        val cells = Cells.of(positions, minePositions)

        // then
        assertAll({
            assertThat(cells[0].position).isEqualTo(minePositions[0])
            assertThat(cells[1].position).isEqualTo(minePositions[1])
            assertThat(cells[2].position).isEqualTo(minePositions[2])
        })
    }

    @Test
    fun `셀에서 지뢰가 아닌 경우 인접한 지뢰가 없다면 값은 0이다`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position.of(1, 1), Position.of(1, 2), Position.of(1, 3)))
        val cells = Cells.of(positions, minePositions)

        // then
        assertAll({
            assertThat(cells[4].state.value).isEqualTo(0)
        })
    }

    @Test
    fun `셀에서 지뢰가 아닌 경우 인접한 지뢰가 있다면 인접한 지뢰 갯수를 표시 해준다`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position.of(1, 1), Position.of(1, 2), Position.of(1, 3)))
        val cells = Cells.of(positions, minePositions)

        // then
        assertAll({
            assertThat(cells[3].state.value).isEqualTo(1)
        })
    }

    @Test
    fun `셀에서 지뢰인 경우 값은 -1이다`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position.of(1, 1), Position.of(1, 2), Position.of(1, 3)))
        val cells = Cells.of(positions, minePositions)

        // then
        assertAll({
            assertThat(cells[1].state.value).isEqualTo(-1)
        })
    }
}
