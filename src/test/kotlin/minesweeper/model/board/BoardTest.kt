package minesweeper.model.board

import minesweeper.fixture.cellAtOrNull
import minesweeper.fixture.toBoard
import minesweeper.model.coordinate.BoardArea
import minesweeper.model.coordinate.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class BoardTest {

    @ParameterizedTest
    @CsvSource(
        "10,10,1", // rowCount, columnCount, mineCount
        "10,20,2",
        "10,10,10",
        "50,50,5"
    )
    fun `높이 x 너비를 갖는 맵 생성 테스트`(rowCount: Int, columnCount: Int, expectedMineCount: Int) {

        // given
        val boardArea = BoardArea.of(rowCount, columnCount)

        // when
        val actualCells = Board(boardArea) { position, _ ->
            boardArea.indexOf(position) < expectedMineCount
        }.apply {
            this.openCell(Position(rowCount - 1, columnCount - 1))
        }.cells

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
        assertThrows<IllegalArgumentException> { Board(BoardArea.of(rowCount, columnCount)) { _, _ -> true } }
    }

    @Test
    fun `지뢰가 없는 인접한 칸이 모두 열림`() {

        // given
        val board = listOf(
            "--*",
            "--*",
            "**X"
        ).toBoard()

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
        val board = listOf(
            "01X",
            "11*",
            "*XX"
        ).toBoard()

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
        val board = listOf(
            "*--",
            "---",
            "---"
        ).toBoard()

        // when
        val actual = board
            .apply { openCell(Position(0, 0)) }
            .state as? BoardState.Finished

        // then
        val expected = BoardState.Finished(
            area = board.area,
            cells = board.cells,
            isWin = false
        )
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `안전 셀 모두 열어서 끝남`() {

        // given
        val board = listOf(
            "*--",
            "---",
            "---"
        ).toBoard()

        // when
        val actual = board
            .apply { openCell(Position(2, 2)) }
            .state as? BoardState.Finished

        // then
        val expected = BoardState.Finished(
            area = board.area,
            cells = board.cells,
            isWin = true
        )
        assertThat(actual).isEqualTo(expected)
    }
}
