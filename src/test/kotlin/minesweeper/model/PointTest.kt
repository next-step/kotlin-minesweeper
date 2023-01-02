package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PointTest {
    @Test
    fun plus() {
        val point = Point(0, 0)
        val point2 = Point(1, 1)
        val point3 = point + point2
        assertThat(point3.x).isEqualTo(point.x + point2.x)
        assertThat(point3.y).isEqualTo(point.y + point2.y)
    }
}
