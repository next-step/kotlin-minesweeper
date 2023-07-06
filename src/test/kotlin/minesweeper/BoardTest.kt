package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.FixedCoordinatesGenerator
import minesweeper.domain.RandomCoordinatesGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest {
    @Test
    fun `높이, 너비, 지뢰 개수로 보드를 생성할 수 있다`() {
        // given
        val height = 3
        val width = 3
        val numberOfMines = 2

        // when
        val minesCoordinates = RandomCoordinatesGenerator(height, width).create(numberOfMines)

        // then
        assertThat(Board.create(3, 3, minesCoordinates)).isInstanceOf(Board::class.java)
    }

    @Test
    fun `보드의 셀 개수보다 지뢰 개수가 많으면 IllegalArgumentException`() {
        // given
        val height = 3
        val width = 3
        val numberOfMines = 30

        // when then
        assertThrows<IllegalArgumentException> {
            RandomCoordinatesGenerator(height, width).create(numberOfMines)
        }
    }

    @Test
    fun `보드에서 지뢰를 찾을 수 있다`() {
        // given
        val height = 3
        val width = 3
        val numberOfMines = 1

        // when
        val mineCoordinates = FixedCoordinatesGenerator(height, width).create(listOf(Pair(1, 2)))
        val board = Board.create(height, width, mineCoordinates)

        // then
        assertThat(board.isMineCell(1, 2)).isTrue()
    }

    @Test
    fun `보드에서 지뢰가 아닌 셀을 찾을 수 있다`() {
        // given
        val height = 3
        val width = 3
        val numberOfMines = 1

        // when
        val mineCoordinates = FixedCoordinatesGenerator(height, width).create(listOf(Pair(1, 2)))
        val board = Board.create(height, width, mineCoordinates)

        // then
        assertThat(board.isMineCell(2, 2)).isFalse()
    }
}
