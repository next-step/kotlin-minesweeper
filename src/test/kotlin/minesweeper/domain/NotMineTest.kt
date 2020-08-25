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
        assertThat(point.isOpen).isFalse()
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
    fun close_point_change_open() {
        val coordinate = Coordinate(1, 1)
        val point = NotMine(coordinate)

        point.openPoint()

        assertThat(point.isOpen).isTrue()
    }
}
