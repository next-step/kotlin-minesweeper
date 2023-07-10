package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.FixedCoordinatesGenerator
import minesweeper.domain.RandomCoordinatesGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
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

        // when
        val mineCoordinates = FixedCoordinatesGenerator(height, width).create(listOf(Pair(1, 2)))
        val board = Board.create(height, width, mineCoordinates)

        // then
        assertThat(board.isMineCell(2, 2)).isFalse()
    }

    @Test
    fun `보드 특정 셀에서 자신을 제외한 주변 8개 셀에 포함된 지뢰의 개수를 계산할 수 있다`() {
        // given
        val height = 5
        val width = 5
        val mineCoordinates = FixedCoordinatesGenerator(height, width).create(listOf(1 to 2, 2 to 2))
        val board = Board.create(height, width, mineCoordinates)

        // when
        val x1 = 1
        val y1 = 1

        val x2 = 3
        val y2 = 3

        val x3 = 4
        val y3 = 4

        // then
        assertAll({
            assertThat(board.countMinesNearby(x1, y1)).isEqualTo(2)
            assertThat(board.countMinesNearby(x2, y2)).isEqualTo(1)
            assertThat(board.countMinesNearby(x3, y3)).isEqualTo(0)
        })
    }

    @Test
    fun `보드 특정 셀에서 자신을 제외한 주변 8개 셀에 포함된 지뢰의 개수를 계산할 때 보드 밖의 좌표를 입력할 경우 IndexOutOfBoundsException`() {
        // given
        val height = 5
        val width = 5
        val mineCoordinates = FixedCoordinatesGenerator(height, width).create(listOf(1 to 2, 2 to 2))
        val board = Board.create(height, width, mineCoordinates)

        // when
        val x = 5
        val y = 5

        // then
        assertThrows<IndexOutOfBoundsException> {
            board.countMinesNearby(x, y)
        }
    }
}
