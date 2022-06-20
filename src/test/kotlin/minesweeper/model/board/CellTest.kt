package minesweeper.model.board

import minesweeper.model.board.coordinate.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CellTest {

    @Test
    fun `지뢰셀 카운트 테스트`() {

        // given
        val cellList = listOf(
            Cell.Mine(Position(1, 1)),
            Cell.Mine(Position(2, 1)),
            Cell.Mine(Position(3, 1))
        )

        val expectedMineCount = 3
        val expectedSafeCellCount = 0

        // when
        val cells = Cells(cellList)

        // then
        assertAll(
            { assertThat(cells.mineCount).isEqualTo(expectedMineCount) },
            { assertThat(cells.safeCellCount).isEqualTo(expectedSafeCellCount) },
            { assertThat(cells.count()).isEqualTo(expectedMineCount + expectedSafeCellCount) }
        )
    }

    @Test
    fun `안전셀 카운트 테스트`() {

        // given
        val cellList = listOf(
            Cell.Safe(Position(1, 1)),
            Cell.Safe(Position(2, 1)),
            Cell.Safe(Position(3, 1))
        )

        val expectedMineCount = 0
        val expectedSafeCellCount = 3

        // when
        val cells = Cells(cellList)

        // then
        assertAll(
            { assertThat(cells.mineCount).isEqualTo(expectedMineCount) },
            { assertThat(cells.safeCellCount).isEqualTo(expectedSafeCellCount) },
            { assertThat(cells.count()).isEqualTo(expectedMineCount + expectedSafeCellCount) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "3,3", // mineCellCount, safeCellCount
        "5,10"
    )
    fun `지뢰셀 안전셀 카운트 테스트`(expectedMineCount: Int, expectedSafeCellCount: Int) {

        // given
        val cellList = mutableListOf<Cell>()
        repeat(expectedMineCount) {
            cellList.add(Cell.Mine(Position(cellList.count(), 1)))
        }

        repeat(expectedSafeCellCount) {
            cellList.add(Cell.Safe(Position(cellList.count(), 1)))
        }

        // when
        val cells = Cells(cellList)

        // then
        assertAll(
            { assertThat(cells.mineCount).isEqualTo(expectedMineCount) },
            { assertThat(cells.safeCellCount).isEqualTo(expectedSafeCellCount) },
            { assertThat(cells.count()).isEqualTo(expectedMineCount + expectedSafeCellCount) }
        )
    }
}
