package minesweeper.domain

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CoordinateDirectionTest {
    @Test
    fun `오른쪽 좌표 값은 cols를 1 더한 값이다`() {
        CoordinateDirection.RIGHT.rows shouldBe 1
        CoordinateDirection.RIGHT.cols shouldBe 0
    }

    @Test
    fun `왼쪽 좌표 값은 cols를 1 뺀 값이다`() {
        CoordinateDirection.LEFT.rows shouldBe -1
        CoordinateDirection.LEFT.cols shouldBe 0
    }

    @Test
    fun `위쪽 좌표 값은 rows를 1 더한 값이다`() {
        CoordinateDirection.UP.rows shouldBe 0
        CoordinateDirection.UP.cols shouldBe 1
    }

    @Test
    fun `아래쪽 좌표 값은 rows를 1 뺀 값이다`() {
        CoordinateDirection.DOWN.rows shouldBe 0
        CoordinateDirection.DOWN.cols shouldBe -1
    }

    @Test
    fun `오른쪽 위의 좌표 값은 cols를 1 더하고 rows를 1 더한 값이다`() {
        CoordinateDirection.RIGHT_UP.rows shouldBe 1
        CoordinateDirection.RIGHT_UP.cols shouldBe 1
    }

    @Test
    fun `왼쪽 위의 좌표 값은 cols를 1 빼고 rows를 1 더한 값이다`() {
        CoordinateDirection.LEFT_UP.rows shouldBe -1
        CoordinateDirection.LEFT_UP.cols shouldBe 1
    }

    @Test
    fun `오른쪽 아래의 좌표 값은 cols를 1 더하고 rows를 1 뺀 값이다`() {
        CoordinateDirection.RIGHT_DOWN.rows shouldBe 1
        CoordinateDirection.RIGHT_DOWN.cols shouldBe -1
    }

    @Test
    fun `왼쪽 아래의 좌표 값은 cols를 1 빼고 rows를 1 뺀 값이다`() {
        CoordinateDirection.LEFT_DOWN.rows shouldBe -1
        CoordinateDirection.LEFT_DOWN.cols shouldBe -1
    }

    @Test
    fun `중심 좌표가 주어진다면 해당 좌표의 인접한 여덟개의 좌표 값을 반환한다`() {
        val coordinate = Coordinate(2, 2)

        CoordinateDirection.around(coordinate) shouldContainExactlyInAnyOrder listOf(
            Coordinate(1, 1),
            Coordinate(2, 1),
            Coordinate(3, 1),
            Coordinate(1, 2),
            Coordinate(3, 2),
            Coordinate(1, 3),
            Coordinate(2, 3),
            Coordinate(3, 3)
        )
    }
}
