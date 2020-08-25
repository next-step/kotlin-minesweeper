package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NotMineTest {
    @Test
    fun make_point() {
        val coordinate = Coordinate(1, 1)

        val point = NotMine(coordinate)

        assertThat(point.isMine()).isFalse()
        assertThat(point.coordinate).isEqualTo(coordinate)
        assertThat(point.isOpen).isTrue()
    }

    @Test
    fun is_it() {
        val coordinate = Coordinate(1, 1)
        val point = NotMine(coordinate)

        assertThat(point.isItCoordinate(coordinate)).isTrue()
    }

    @Test
    fun is_last_x() {
        val coordinate = Coordinate(1, 1)
        val point = NotMine(coordinate)
        val lastX = 2
        assertThat(point.isLastX(lastX)).isFalse()
    }

    @Test
    fun open_point_change_close() {
        val coordinate = Coordinate(1, 1)
        val point = NotMine(coordinate)

        point.closePoint()

        assertThat(point.isOpen).isFalse()
    }

    @Test
    fun close_point_change_open() {
        val coordinate = Coordinate(1, 1)
        val point = NotMine(coordinate)
        point.closePoint()

        point.openPoint()

        assertThat(point.isOpen).isTrue()
    }

    @Test
    fun can_not_get_is_mine_in_close_point() {
        val point = NotMine(Coordinate(1, 1))
        point.closePoint()

        assertThat(point.isMine()).isNull()
    }
}
