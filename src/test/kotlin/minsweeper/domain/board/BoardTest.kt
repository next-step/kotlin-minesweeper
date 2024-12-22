package minsweeper.domain.board

import minsweeper.CELLS
import minsweeper.domain.Coordinate
import minsweeper.domain.result.OpenResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `지뢰를 오픈하면 MINE_FOUND를 반환해야 한다`() {
        // given
        val board = Board(BoardSize(10, 10), CELLS)
        val coordinate = Coordinate.of(1, 3)

        // when
        val result = board.open(coordinate)

        // then
        assertThat(result).isEqualTo(OpenResult.MINE_FOUND)
    }

    @Test
    fun `잘못된 좌표를 오픈하면 INVALID_COORDINATE를 반환해야 한다`() {
        // given
        val board = Board(BoardSize(10, 10), CELLS)
        val coordinate = Coordinate.of(1, 4)

        // when
        val result = board.open(coordinate)

        // then
        assertThat(result).isEqualTo(OpenResult.INVALID_COORDINATE)
    }

    @Test
    fun `일반 땅을 오픈하면 SUCCESS를 반환해야 한다`() {
        // given
        val board = Board(BoardSize(10, 10), CELLS)
        val coordinate = Coordinate.of(1, 2)

        // when
        val result = board.open(coordinate)

        // then
        assertThat(result).isEqualTo(OpenResult.SUCCESS)
    }

    @Test
    fun `주변 지뢰가 0인 곳을 오픈하면 주변 셀도 오픈되어야 한다`() {
        // given
        val board = Board(BoardSize(3, 3), CELLS)

        // when
        board.open(Coordinate.of(1, 1))

        // then
        assertThat(board.coordinatedCells[Coordinate.of(1, 1)]?.isOpened).isTrue()
        assertThat(board.coordinatedCells[Coordinate.of(1, 2)]?.isOpened).isTrue()
        assertThat(board.coordinatedCells[Coordinate.of(1, 3)]?.isOpened).isFalse()
        assertThat(board.coordinatedCells[Coordinate.of(2, 1)]?.isOpened).isTrue()
        assertThat(board.coordinatedCells[Coordinate.of(2, 2)]?.isOpened).isTrue()
        assertThat(board.coordinatedCells[Coordinate.of(2, 3)]?.isOpened).isTrue()
        assertThat(board.coordinatedCells[Coordinate.of(3, 1)]?.isOpened).isTrue()
        assertThat(board.coordinatedCells[Coordinate.of(3, 2)]?.isOpened).isTrue()
        assertThat(board.coordinatedCells[Coordinate.of(3, 3)]?.isOpened).isTrue()
    }

    @Test
    fun `주변 지뢰가 1인 곳을 오픈하면 해당 셀만 오픈되어야 한다`() {
        // given
        val board = Board(BoardSize(3, 3), CELLS)

        // when
        board.open(Coordinate.of(1, 2))

        // then
        assertThat(board.coordinatedCells[Coordinate.of(1, 1)]?.isOpened).isFalse()
        assertThat(board.coordinatedCells[Coordinate.of(1, 2)]?.isOpened).isTrue()
        assertThat(board.coordinatedCells[Coordinate.of(1, 3)]?.isOpened).isFalse()
        assertThat(board.coordinatedCells[Coordinate.of(2, 1)]?.isOpened).isFalse()
        assertThat(board.coordinatedCells[Coordinate.of(2, 2)]?.isOpened).isFalse()
        assertThat(board.coordinatedCells[Coordinate.of(2, 3)]?.isOpened).isFalse()
        assertThat(board.coordinatedCells[Coordinate.of(3, 1)]?.isOpened).isFalse()
        assertThat(board.coordinatedCells[Coordinate.of(3, 2)]?.isOpened).isFalse()
        assertThat(board.coordinatedCells[Coordinate.of(3, 3)]?.isOpened).isFalse()
    }

}