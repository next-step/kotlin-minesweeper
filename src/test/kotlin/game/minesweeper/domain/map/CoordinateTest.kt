package game.minesweeper.domain.map

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("좌표")
internal class CoordinateTest {

    @Test
    fun `같은 좌표 비교`() {
        val coordinate = Coordinate(1, 1)
        val other = Coordinate(1, 1)
        assertThat(coordinate).isEqualTo(other)
    }

    @Test
    fun `다른 좌표 비교`() {
        val coordinate = Coordinate(1, 1)
        val other = Coordinate(1, 2)
        assertThat(coordinate).isNotEqualTo(other)
    }

    @Test
    fun `올바르지 않은 좌표는 예외`() {
        assertThrows<IllegalArgumentException> { Coordinate(0, 0) }
    }

    @Test
    fun `주변 8개 좌표 얻기`() {
        val coordinate = Coordinate(5, 5)
        val border = coordinate.findBorder(10, 10)
        assertThat(border).hasSize(8)
    }

    @Test
    fun `위 경계에서 주변 좌표 얻기`() {
        val coordinate = Coordinate(1, 5)
        val border = coordinate.findBorder(10, 10)
        assertThat(border).hasSize(5)
    }

    @Test
    fun `아래 경계에서 주변 좌표 얻기`() {
        val coordinate = Coordinate(10, 5)
        val border = coordinate.findBorder(10, 10)
        assertThat(border).hasSize(5)
    }

    @Test
    fun `왼쪽 경계에서 주변 좌표 얻기`() {
        val topLeft = Coordinate(1, 1)
        val left = Coordinate(5, 1)
        val bottomLeft = Coordinate(10, 1)

        assertThat(topLeft.findBorder(10, 10)).hasSize(3)
        assertThat(left.findBorder(10, 10)).hasSize(5)
        assertThat(bottomLeft.findBorder(10, 10)).hasSize(3)
    }

    @Test
    fun `오른쪽 경계에서 주변 좌표 얻기`() {
        val topRight = Coordinate(1, 10)
        val right = Coordinate(5, 10)
        val bottomRight = Coordinate(10, 10)

        assertThat(topRight.findBorder(10, 10)).hasSize(3)
        assertThat(right.findBorder(10, 10)).hasSize(5)
        assertThat(bottomRight.findBorder(10, 10)).hasSize(3)
    }
}
