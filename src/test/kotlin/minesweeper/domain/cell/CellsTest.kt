package minesweeper.domain.cell

import minesweeper.domain.board.BoardSize
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

fun mineCell(positions: List<Position>, allPositions: Positions): Cells {
    val value = Positions(positions).onEach {
        it.updateAdjacentPositions(allPositions)
    }.map {
        MineCell(it)
    }
    return Cells(value)
}

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
            assertThat(cells[4].getCellAdjacentCount()).isEqualTo(0)
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
            assertThat(cells[3].getCellAdjacentCount()).isEqualTo(1)
        })
    }

    @Test
    fun `셀에서 지뢰인 경우 값은 -1이다`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position.of(1, 1), Position.of(1, 2), Position.of(1, 3)))
        val cells = Cells.of(positions, minePositions)

        // then
        assertThat(cells[1].getCellAdjacentCount()).isEqualTo(-1)
    }

    @Test
    fun `지뢰를 오픈 했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions, minePositions)

        // when
        val cellsState = cells.open(Position.of(1, 1))

        // then
        assertThat(cellsState).isEqualTo(CellsState.BOMB)
    }

    @Test
    fun `지뢰를 제외 한 나머지를 오픈했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(1, 4))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions, minePositions)

        // when
        val cellsState = cells.open(Position.of(4, 1))

        // then
        assertThat(cellsState).isEqualTo(CellsState.NOT_EXIST_MINE)
    }

    @Test
    fun `지뢰가 아닌 셀을 오픈했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions =
            Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(4, 1), Position.of(6, 1)))
        val cells = Cells.of(positions, minePositions)

        // when
        val cellsState = cells.open(Position.of(10, 5))

        // then
        assertThat(cellsState).isEqualTo(CellsState.EXIST_MINE)
    }

    @Test
    fun `잘못 된 셀을 오픈했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(1, 4))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions, minePositions)

        // when
        val actual = runCatching { cells.open(Position.of(4, 99)) }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("해당 셀을 찾을 수 없습니다.")
    }

    @Test
    fun `값이 0인 셀을 오픈했을 경우 열리는 셀 갯수 테스트`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions, minePositions)

        // when
        cells.open(Position.of(4, 4))

        // then

        assertThat(cells.count { !it.isHiddenCell }).isEqualTo(97)
    }

    @Test
    fun `처음 만들어진 셀은 숨김처리 되어 있다`() {
        // given
        val cell = Cell.of(Position.of(1, 1))

        // then
        assertThat(cell.isHiddenCell).isTrue
    }

    @Test
    fun `해당 셀을 보이도록 만듬`() {
        // given
        val cell = Cell.of(Position.of(1, 1))

        // when
        cell.openCell()

        // then
        assertThat(cell.isHiddenCell).isFalse
    }
}
