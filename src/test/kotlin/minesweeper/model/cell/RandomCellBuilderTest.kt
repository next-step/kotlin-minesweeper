package minesweeper.model.cell

import minesweeper.fixture.cellAtOrNull
import minesweeper.model.board.Board
import minesweeper.model.coordinate.BoardArea
import minesweeper.model.coordinate.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class RandomCellBuilderTest {

    @ParameterizedTest
    @CsvSource(
        "10,10,0,1", // rowCount, columnCount, mineCount , expectedMineCount
        "10,20,-1,1",
        "10,10,101,99",
        "5,5,1000,24"
    )
    fun `랜던 맵 생성 지뢰 갯수 범위 테스트`(rowCount: Int, columnCount: Int, mineCount: Int, expectedMineCount: Int) {

        val boardArea = BoardArea.of(rowCount, columnCount)
        val actualMineCount = Board(boardArea, RandomCellBuilder(boardArea, mineCount))
            .apply { openCell(Position(0, 0)) }
            .cells.mineCount
        assertThat(actualMineCount).isEqualTo(expectedMineCount)
    }

    @ParameterizedTest
    @CsvSource(
        "10,10,1", // rowCount, columnCount, mineCount
        "10,20,2",
        "10,10,10",
        "50,50,5"
    )
    fun `랜던 맵 생성 테스트`(rowCount: Int, height: Int, expectedMineCount: Int) {
        // given
        val boardArea = BoardArea.of(rowCount, height)

        // when
        val actualBoard = Board(boardArea, RandomCellBuilder(boardArea, expectedMineCount))
            .apply { this.openCell(Position(0, 0)) }

        val actualCells = actualBoard.cells
        val expectedCellCount = rowCount * height
        val expectedSafeCellCount = expectedCellCount - expectedMineCount

        // then
        assertAll(
            { assertThat(actualCells.count()).isEqualTo(expectedCellCount) },
            { assertThat(actualCells.mineCount).isEqualTo(expectedMineCount) },
            { assertThat(actualCells.safeCellCount).isEqualTo(expectedSafeCellCount) }
        )
    }

    @RepeatedTest(10)
    fun `처음 클릭한 셀은 무조건 안전함`() {

        // given
        val boardArea = BoardArea.of(100, 100)
        val expectedSafeCount = 1
        val expectedMineCount = boardArea.cellCount - expectedSafeCount
        val board = Board(boardArea, RandomCellBuilder(boardArea, expectedMineCount))
        val randomClickPosition = board.shuffled()[0]

        // when
        board.openCell(randomClickPosition)
        val actualClickCell = board.cellAtOrNull(randomClickPosition)

        // then
        assertAll(
            { assertThat(actualClickCell).isInstanceOf(Cell.Safe::class.java) },
            { assertThat(board.cells.count { it is Cell.Safe }).isEqualTo(expectedSafeCount) },
            { assertThat(board.cells.count { it is Cell.Mine }).isEqualTo(expectedMineCount) }
        )
    }
}
