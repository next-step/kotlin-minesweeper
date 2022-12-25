package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MineMapTest {
    @Test
    internal fun `높이와 너비는 각각 행크기, 열크기가 된다`() {
        val height = 10
        val width = 10
        val mineMap = MineMap.of(height, width)
        assertThat(mineMap.rowSize).isEqualTo(height)
        assertThat(mineMap.columnSize).isEqualTo(width)
    }

    @ParameterizedTest
    @CsvSource(
        value = ["0:0", "1:0", "0:1", "-1:0", "0:-1", "-1:-1"], delimiter = ':'
    )
    internal fun `크기가 0보다 작은 지뢰판은 생성할 수 없다`(height: Int, width: Int) {
        assertThrows<IllegalArgumentException> { MineMap.of(height, width) }
    }

    @Test
    internal fun `지뢰를 심으면 주변 숫자가 증가한다`() {
        val mines = Mines(setOf(Cell(0, 0), Cell(1, 1), Cell(1, 2)))
        val mineMap = MineMap.of(3, 3)

        mineMap.plantMines(mines)

        // 테스트 지뢰판 모양
        // * 2 1
        // 3 * 2
        // 2 * 2
        assertThat(mineMap.getNearCount(Cell(1, 0))).isEqualTo(2)
        assertThat(mineMap.getNearCount(Cell(2, 0))).isEqualTo(1)
        assertThat(mineMap.getNearCount(Cell(0, 1))).isEqualTo(3)
        assertThat(mineMap.getNearCount(Cell(2, 1))).isEqualTo(2)
        assertThat(mineMap.getNearCount(Cell(0, 2))).isEqualTo(2)
        assertThat(mineMap.getNearCount(Cell(2, 2))).isEqualTo(2)
    }
}
