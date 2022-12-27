package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MineBoardTest {
    @Test
    internal fun `지뢰 갯수는 높이x너비 값을 넘을 수 없다`() {
        val mineMap = MineMap.of(10, 10)
        val mines = Mines(mineMap.selectAllCells().toSet())
        assertThat(mines.size).isEqualTo(100)
        assertThrows<IllegalArgumentException> { MineBoard(mineMap, mines) }
    }

    @ParameterizedTest
    @CsvSource(value = ["-1:0", "0:-1", "10:9", "9:10"], delimiter = ':')
    internal fun `지뢰는 지뢰판 범위를 넘어갈 수 없다`(x: Int, y: Int) {
        val mineMap = MineMap.of(10, 10)
        val mines = Mines(setOf(Cell(x, y)))
        assertThrows<IllegalArgumentException> { MineBoard(mineMap, mines) }
    }

    @Test
    internal fun `특정 좌표의 지뢰 여부를 판단한다`() {
        val mines = Mines(setOf(Cell(0, 0), Cell(1, 1)))
        val mineBoard = MineBoard(MineMap.of(10, 10), mines)

        assertThat(mineBoard.checkMine(Cell(0, 0))).isTrue
        assertThat(mineBoard.checkMine(Cell(1, 1))).isTrue
        assertThat(mineBoard.checkMine(Cell(1, 0))).isFalse
    }
}
