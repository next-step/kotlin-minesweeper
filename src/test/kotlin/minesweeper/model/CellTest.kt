package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CellTest {
    @ParameterizedTest
    @CsvSource(
        value = [
            "0:0 : 0:0 : true",
            "1:5 : 1:5 : true",
            "1:2 : 2:1 : false",
            "2:2 : 3:3 : false",
        ],
        delimiter = ':'
    )
    internal fun `두 셀이 동일한 위치인지 확인한다`(x1: Int, y1: Int, x2: Int, y2: Int, expected: Boolean) {
        val cell1 = Cell(x1, y1)
        val cell2 = Cell(x2, y2)
        assertThat(cell1 == cell2).isEqualTo(expected)
    }

    @Test
    internal fun `특정 셀의 주변 8방향 셀을 구한다`() {
        val target = Cell(1, 0)
        val expectedCells = arrayOf(
            Cell(0, -1), Cell(1, -1), Cell(2, -1),
            Cell(0, 0), Cell(2, 0),
            Cell(0, 1), Cell(1, 1), Cell(2, 1),
        )

        val actualCells = target.getNearCells()
        assertThat(actualCells).containsExactlyInAnyOrder(*expectedCells)
    }
}
