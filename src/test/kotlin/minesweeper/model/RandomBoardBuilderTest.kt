package minesweeper.model

import minesweeper.model.board.coordinate.BoardArea
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class RandomBoardBuilderTest {

    @ParameterizedTest
    @CsvSource(
        "1,1,0,1", // rowCount, columnCount, mineCount , expectedMineCount
        "1,2,-1,1",
        "10,10,101,100",
        "5,5,1000,25"
    )
    fun `랜던 맵 생성 지뢰 갯수 범위 테스트`(rowCount: Int, columnCount: Int, mineCount: Int, expectedMineCount: Int) {

        val boardArea = BoardArea.of(rowCount, columnCount)
        val actualMineCount = RandomBoardBuilder(boardArea, mineCount)
            .createNewBoard()
            .cells.mineCount
        assertThat(actualMineCount).isEqualTo(expectedMineCount)
    }

    @ParameterizedTest
    @CsvSource(
        "1,1,1", // rowCount, columnCount, mineCount
        "1,2,2",
        "10,10,10",
        "5,5,5"
    )
    fun `랜던 맵 생성 테스트`(rowCount: Int, height: Int, expectedMineCount: Int) {
        // given
        val boardArea = BoardArea.of(rowCount, height)

        // when
        val actualBoard = RandomBoardBuilder(boardArea, expectedMineCount).createNewBoard()
        val actualCells = actualBoard.cells
        val expectedCellCount = rowCount * height
        val expectedSafeCellCount = expectedCellCount - expectedMineCount

        // then
        assertAll(
            { Assertions.assertThat(actualCells.count()).isEqualTo(expectedCellCount) },
            { Assertions.assertThat(actualCells.mineCount).isEqualTo(expectedMineCount) },
            { Assertions.assertThat(actualCells.safeCellCount).isEqualTo(expectedSafeCellCount) }
        )
    }
}
