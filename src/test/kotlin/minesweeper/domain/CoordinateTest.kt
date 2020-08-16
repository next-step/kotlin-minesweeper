package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CoordinateTest {
    @Test
    fun make_coordinate() {
        val coordinate = Coordinate(1, 1)

        assertThat(coordinate.x).isEqualTo(1)
        assertThat(coordinate.y).isEqualTo(1)
    }

    @Test
    fun is_x() {
        val coordinate = Coordinate(1, 1)
        val x = 1

        assertThat(coordinate.isX(x)).isTrue()
    }

    @Test
    fun is_Y() {
        val coordinate = Coordinate(1, 1)
        val y = 1

        assertThat(coordinate.isY(y)).isTrue()
    }
}
