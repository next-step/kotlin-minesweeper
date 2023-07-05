package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.RandomCoordinatesGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {
    @Test
    fun `높이, 너비, 지뢰 개수로 보드를 생성할 수 있다`() {
        val height = 3
        val width = 3
        val numberOfMines = 2

        val minesCoordinates = RandomCoordinatesGenerator(height, width).create(numberOfMines)
        assertThat(Board.create(3, 3, minesCoordinates)).isInstanceOf(Board::class.java)
    }

    @Test
    fun `보드의 셀 개수보다 지뢰 개수가 많으면 IllegalArgumentException`() {
        val height = 3
        val width = 3
        val numberOfMines = 30
        assertThrows<IllegalArgumentException> {
            RandomCoordinatesGenerator(height, width).create(numberOfMines)
        }
    }
}
