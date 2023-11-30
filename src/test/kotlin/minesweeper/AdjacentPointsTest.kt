package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AdjacentPointsTest {
    @Test
    fun `8방면 포인트는 row 및 column 사이에 있는 포인트 묶음을 반환한다`() {
        val topRight = AdjacentPoints.create(Point(0, 0), 3, 3)
        assertThat(topRight.points.size).isEqualTo(3)
        assertThat(topRight.points.contains(Point(1, 0))).isEqualTo(true)
        assertThat(topRight.points.contains(Point(1, 1))).isEqualTo(true)
        assertThat(topRight.points.contains(Point(0, 1))).isEqualTo(true)

        val bottomLeft = AdjacentPoints.create(Point(2, 2), 3, 3)
        assertThat(bottomLeft.points.size).isEqualTo(3)
        assertThat(bottomLeft.points.contains(Point(1, 2))).isEqualTo(true)
        assertThat(bottomLeft.points.contains(Point(1, 1))).isEqualTo(true)
        assertThat(bottomLeft.points.contains(Point(2, 1))).isEqualTo(true)

        val center = AdjacentPoints.create(Point(1, 1), 3, 3)
        assertThat(center.points.size).isEqualTo(8)
    }
}
