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
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))

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
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))

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
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))

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
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))

        // then
        assertAll({
            assertThat(cells[1].state.value).isEqualTo(-1)
        })
    }

    @Test
    fun `카운트 수가 포지션 수보다 큰 경우 에러`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val cells = Cells.of(positions)

        // when
        val actual = runCatching { Cells.makeMineCells(cells, 101) }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("카운트 수가 전체 수보다 큽니다.")
    }

    @Test
    fun `지뢰를 오픈 했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))

        // when
        cells.open(Position.of(1, 1))

        // then
        assertThat(cells.isOpenedMine()).isTrue
    }

    @Test
    fun `지뢰를 제외 한 나머지를 오픈했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(1, 4))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))

        // when
        cells.open(Position.of(4, 1))

        // then
        assertThat(cells.isAllOpenedExcludeMine()).isTrue
    }

    @Test
    fun `잘못 된 셀을 오픈했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(1, 4))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))

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
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))

        // when
        cells.open(Position.of(4, 4))

        // then

        assertThat(cells.count { !it.state.isHidden }).isEqualTo(97)
    }
}
