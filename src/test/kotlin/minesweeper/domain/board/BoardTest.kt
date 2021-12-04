package minesweeper.domain.board

import minesweeper.domain.cell.Cells
import minesweeper.domain.cell.CellsState
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BoardTest {

    @Test
    fun `가로 10 세로 10 지뢰 5개인 보드를 만들 경우 100개의 셀과 5개의 지뢰가 만들어 진다`() {
        // given
        val boardSize = BoardSize(10, 10)
        val mineCount = 5
        val board = Board.of(boardSize, mineCount)

        // when
        val cellCounts = board.cells.size
        val boardMineCount = board.cells.filter { it.state.value == -1 }.size

        // then
        assertAll({
            assertThat(cellCounts).isEqualTo(100)
            assertThat(boardMineCount).isEqualTo(mineCount)
        })
    }

    @Test
    fun `지뢰를 오픈 했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(10, 10))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))
        val board = Board(cells)

        // when
        val boardState = board.open(Position.of(1, 1))

        // then
        assertThat(boardState).isEqualTo(CellsState.BOMB)
    }

    @Test
    fun `지뢰를 제외 한 나머지를 오픈했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(1, 4))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))
        val board = Board(cells)

        // when
        val boardState = board.open(Position.of(4, 1))

        // then
        assertThat(boardState).isEqualTo(CellsState.NOT_EXIST_MINE)
    }

    @Test
    fun `지뢰가 아닌 셀을 오픈했을 경우`() {
        // given
        val positions = Positions.of(BoardSize.of(1, 5))
        val minePositions = Positions(listOf(Position.of(3, 1), Position.of(2, 1), Position.of(1, 1)))
        val cells = Cells.of(positions)
        cells.inputMineCells(Cells.of(minePositions))
        val board = Board(cells)

        // when
        val boardState = board.open(Position.of(4, 1))

        // then
        assertThat(boardState).isEqualTo(CellsState.EXIST_MINE)
    }
}
