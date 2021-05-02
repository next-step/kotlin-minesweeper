package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CoordinateDirectionTest {

    @Test
    fun `네방향 좌표를 얻을 수 있다`() {
        val x = 1
        val y = 1
        val expected = listOf(
            Coordinate(x - 1, y),
            Coordinate(x + 1, y),
            Coordinate(x, y - 1),
            Coordinate(x, y + 1)
        )

        val result = CoordinateDirection.getFourWayCoordinates(x, y)

        assertThat(result).hasSameElementsAs(expected)
    }

    @Test
    fun `둘러싸인 좌표를 얻을 수 있다`() {
        val x = 1
        val y = 1
        val expected = listOf(
            Coordinate(x, y - 1),
            Coordinate(x, y + 1),
            Coordinate(x - 1, y),
            Coordinate(x + 1, y),
            Coordinate(x - 1, y - 1),
            Coordinate(x - 1, y + 1),
            Coordinate(x + 1, y + 1),
            Coordinate(x + 1, y - 1)
        )

        val result = CoordinateDirection.getSurroundingAllCoordinates(x, y)

        assertThat(result).hasSameElementsAs(expected)
    }
}
