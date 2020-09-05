package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun find_point() {
        val board = Board(10, 10)

        val point = board.findPoint(2, 3)!!

        assertThat(point.coordinate).isEqualTo(Coordinate(2, 3))
    }

    @Test
    fun find_error() {
        val board = Board(10, 10)

        assertThat(board.findPoint(10, 10)).isNull()
    }

    @Test
    fun open_point() {
        val board = Board(10, 10)

        board.open(Coordinate(1, 1))

        assertThat(board.findPoint(1, 1)!!.isOpen()).isTrue()
    }

    @Test
    fun is_player_lose() {
        val board = Board(10, 10)
        val point = Mine(Coordinate(1, 1))

        val result = board.isPlayerWin(point)

        assertThat(result).isFalse()
    }

    @Test
    fun is_player_win() {
        val board = Board(10, 10)
        val point = NotMine(Coordinate(1, 1))
        board.open(point.coordinate)

        val result = board.isPlayerWin(point)

        assertThat(result).isTrue()
    }
}
