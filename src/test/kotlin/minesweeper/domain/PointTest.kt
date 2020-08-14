package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun make_point() {
        val coordinate = Coordinate(1, 1)
        val isMine = true

        val point = Point(coordinate, isMine)

        assertThat(point.isMine).isTrue()
        assertThat(point.coordinate).isEqualTo(coordinate)
    }
}
