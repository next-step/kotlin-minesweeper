package minesweeper.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointsTest {
    @Test
    fun make_points() {
        val coordinates = Coordinates(2, 1)
        val mineCoordinates = listOf(Coordinate(0, 0))

        val points = Points(coordinates, mineCoordinates)

        assertThat(points.points).hasSize(2)
        assertThat(points.findPoint(0, 0).isMine()).isTrue()
    }

    @Test
    fun find_point() {
        val coordinates = Coordinates(10, 10)
        val points = Points(coordinates)

        val point = points.findPoint(2, 3)

        assertThat(point.coordinate).isEqualTo(Coordinate(2, 3))
    }

    @Test
    fun find_error() {
        val coordinates = Coordinates(10, 10)
        val points = Points(coordinates)

        Assertions.assertThatThrownBy {
            points.findPoint(10, 10)
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessageContaining("10, 10 좌표는 없습니다.")
    }

    @Test
    fun count_mines() {
        val coordinates = Coordinates(10, 10)
        val points = Points(coordinates, coordinates.makeMineCoordinates(10))

        val mineCount = points.countMine()

        assertThat(mineCount).isEqualTo(10)
    }

    @Test
    fun point_around_mines_count() {
        val coordinates = Coordinates(10, 10)
        val mineCoordinates = listOf(Coordinate(0, 1), Coordinate(2, 1), Coordinate(1, 3))
        val points = Points(coordinates, mineCoordinates)
        val point1 = points.findPoint(0, 0)
        val point2 = points.findPoint(1, 1)
        val point3 = points.findPoint(1, 2)

        val result1 = point1.mineCount
        val result2 = point2.mineCount
        val result3 = point3.mineCount

        assertThat(result1).isEqualTo(1)
        assertThat(result2).isEqualTo(2)
        assertThat(result3).isEqualTo(3)
    }
}
