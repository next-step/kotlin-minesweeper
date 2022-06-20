package minesweeper.model.board

import minesweeper.fixture.randomBoard
import minesweeper.model.board.coordinate.BoardArea
import org.assertj.core.api.Assertions.assertThat
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
        val actualCells = Board.randomBoard(boardArea, expectedMineCount).cells
        val expectedCellCount = rowCount * height
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
        "1,1,0", // rowCount, columnCount, mineCount
        "1,2,-1",
        "10,10,101",
        "5,5,1000"
    )
    fun `랜던 맵 생성 지뢰 갯수 범위 테스트`(rowCount: Int, columnCount: Int, expectedMineCount: Int) {
        // given
        val boardArea = BoardArea.of(rowCount, columnCount)
        assertThrows<IllegalArgumentException> { Board.randomBoard(boardArea, expectedMineCount) }
    }
}
