package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun make_board_10x10() {
        val board = Board(10, 10)

        assertThat(board.area).hasSize(100)
    }

    @Test
    fun find_coordinate() {
        val board = Board(10, 10)

        val coordinate = board.findCoordinate(2, 3)

        assertThat(coordinate.x).isEqualTo(2)
        assertThat(coordinate.y).isEqualTo(3)
    }
}
