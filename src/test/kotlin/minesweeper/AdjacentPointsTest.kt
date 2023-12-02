package minesweeper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AdjacentPointsTest {
    @Test
    fun `8방면 포인트는 row 및 column 사이에 있는 포인트 묶음을 반환한다`() {
        val size3x3 = MapSize(LineCount(3), LineCount(3))

        val topRight = Point(1, 1).getAdjacentPoints(size3x3)
        assertThat(topRight.size).isEqualTo(3)
        assertThat(topRight.contains(Point(2, 1))).isEqualTo(true)
        assertThat(topRight.contains(Point(2, 2))).isEqualTo(true)
        assertThat(topRight.contains(Point(1, 2))).isEqualTo(true)

        val bottomLeft = Point(3, 3).getAdjacentPoints(size3x3)
        assertThat(bottomLeft.size).isEqualTo(3)
        assertThat(bottomLeft.contains(Point(2, 3))).isEqualTo(true)
        assertThat(bottomLeft.contains(Point(2, 2))).isEqualTo(true)
        assertThat(bottomLeft.contains(Point(3, 2))).isEqualTo(true)

        val center = Point(2, 2).getAdjacentPoints(size3x3)
        assertThat(center.size).isEqualTo(8)
    }
}
