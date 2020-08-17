package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BoardTest {
    @Test
    fun find_point() {
        val board = BoardFactory.makeBoard(10, 10) { listOf() }

        val point = board.findPoint(2, 3)

        assertThat(point.coordinate).isEqualTo(Coordinate(2, 3))
    }

    @Test
    fun find_error() {
        val board = BoardFactory.makeBoard(10, 10) { listOf() }

        assertThatThrownBy {
            board.findPoint(10, 10)
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessageContaining("10, 10 좌표는 없습니다.")
    }

    @Test
    fun how_many_mines_around_this_point() {
        val board = BoardFactory.makeBoard(10, 10) { listOf(Coordinate(0, 1), Coordinate(0, 2), Coordinate(0, 3)) }
        val point1 = board.findPoint(0, 0)
        val point2 = board.findPoint(1, 1)
        val point3 = board.findPoint(1, 2)

        val result1 = point1.mineCount
        val result2 = point2.mineCount
        val result3 = point3.mineCount

        assertThat(result1).isEqualTo(1)
        assertThat(result2).isEqualTo(2)
        assertThat(result3).isEqualTo(3)
    }
}
