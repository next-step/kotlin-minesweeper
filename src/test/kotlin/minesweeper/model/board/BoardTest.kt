package minesweeper.model.board

import minesweeper.fixture.toBoard
import minesweeper.model.board.coordinate.BoardArea
import minesweeper.model.board.coordinate.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class BoardTest {

    @ParameterizedTest
    @CsvSource(
        "1,1,1", // rowCount, columnCount, mineCount
        "1,2,2",
        "10,10,10",
        "5,5,5"
    )
    fun `높이 x 너비를 갖는 맵 생성 테스트`(rowCount: Int, columnCount: Int, expectedMineCount: Int) {

        // given
        val boardArea = BoardArea.of(rowCount, columnCount)

        // when
        val actualCells = Board.build(boardArea) { position -> boardArea.indexOf(position) < expectedMineCount }
            .cells
        val expectedCellCount = rowCount * columnCount
        val expectedSafeCellCount = expectedCellCount - expectedMineCount

        // then
        assertAll(
            { assertThat(actualCells.count()).isEqualTo(expectedCellCount) },
            { assertThat(actualCells.mineCount).isEqualTo(expectedMineCount) },
            { assertThat(actualCells.safeCellCount).isEqualTo(expectedSafeCellCount) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0,100", // rowCount, columnCount,
        "100,0",
        "0,0"
    )
    fun `맵 크기가 0 이면 에러`(rowCount: Int, columnCount: Int) {
        assertThrows<IllegalArgumentException> { Board.build(BoardArea.of(rowCount, columnCount)) { true } }
    }

    @Test
    fun `지뢰가 없는 인접한 칸이 모두 열림`() {

        // given
        val mineCells = listOf(
            "--*",
            "--*",
            "**X"
        )
        val board = mineCells.toBoard()

        // when
        board.openCell(Position(0, 0))

        // then
        assertAll(
            { assertThat(board.cellAtOrNull(Position(0, 0))?.isOpen).isTrue },
            { assertThat(board.cellAtOrNull(Position(0, 1))?.isOpen).isTrue },
            { assertThat(board.cellAtOrNull(Position(1, 0))?.isOpen).isTrue },
            { assertThat(board.cellAtOrNull(Position(1, 1))?.isOpen).isTrue },
            { assertThat(board.cells.count { it.isOpen }).isEqualTo(4) },
        )
    }

    @Test
    fun `지뢰가 없는 인접한 칸이 모두 열림2`() {

        // given
        val mineCells = listOf(
            "01X",
            "11*",
            "*XX"
        )
        val board = mineCells.toBoard()

        // when
        board.openCell(Position(0, 0))

        // then
        assertAll(
            { assertThat(board.cellAtOrNull(Position(0, 0))?.isOpen).isTrue },
            { assertThat(board.cellAtOrNull(Position(0, 1))?.isOpen).isTrue },

            { assertThat(board.cellAtOrNull(Position(1, 0))?.isOpen).isTrue },
            { assertThat(board.cellAtOrNull(Position(1, 1))?.isOpen).isTrue },

            { assertThat(board.cells.count { it.isOpen }).isEqualTo(4) }
        )
    }

    @Test
    fun `지뢰 열어서 끝남`() {

        // given
        val mineCells = listOf(
            "*--",
            "---",
            "---"
        )
        val board = mineCells.toBoard()

        // when
        board.openCell(Position(0, 0))

        // then
        assertThat(board.state).isEqualTo(BoardState.MINE_EXPLODED)
    }

    @Test
    fun `안전 셀 모두 열어서 끝남`() {

        // given
        val mineCells = listOf(
            "*--",
            "---",
            "---"
        )
        val board = mineCells.toBoard()

        // when
        board.openCell(Position(2, 2))

        // then
        assertThat(board.state).isEqualTo(BoardState.COMPLETED)
    }
}
