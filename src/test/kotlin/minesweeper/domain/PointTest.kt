package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun make_area() {
        val coordinate = Coordinate(1, 1)
        val isMine = true

        val area = Point(coordinate, isMine)

        assertThat(area.isMine).isTrue()
        assertThat(area.coordinate).isEqualTo(coordinate)
    }
}
