package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun make_point() {
        val coordinate = Coordinate(1, 1)
        val isMine = true

        val point = Point(coordinate, isMine)

        assertThat(point.hasMine).isTrue()
        assertThat(point.coordinate).isEqualTo(coordinate)
    }

    @Test
    fun is_it() {
        val coordinate = Coordinate(1, 1)
        val point = Point(coordinate)

        assertThat(point.isItCoordinate(coordinate)).isTrue()
    }

    @Test
    fun is_last_x() {
        val coordinate = Coordinate(1, 1)
        val point = Point(coordinate)
        val lastX = 2
        assertThat(point.isLastX(lastX)).isFalse()
    }
}
