package minsweeper.domain

import minsweeper.CELLS
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `지뢰를 오픈하면 MINE_FOUND를 반환해야 한다`() {
        // given
        val board = Board(CELLS)
        val coordinate = Coordinate(1, 3)

        // when
        val result = board.open(coordinate)

        // then
        assertThat(result).isEqualTo(OpenResult.MINE_FOUND)
    }

    @Test
    fun `잘못된 좌표를 오픈하면 INVALID_COORDINATE를 반환해야 한다`() {
        // given
        val board = Board(CELLS)
        val coordinate = Coordinate(1, 4)

        // when
        val result = board.open(coordinate)

        // then
        assertThat(result).isEqualTo(OpenResult.INVALID_COORDINATE)
    }

    @Test
    fun `일반 땅을 오픈하면 SUCCESS를 반환해야 한다`() {
        // given
        val board = Board(CELLS)
        val coordinate = Coordinate(1, 2)

        // when
        val result = board.open(coordinate)

        // then
        assertThat(result).isEqualTo(OpenResult.SUCCESS)
    }

}