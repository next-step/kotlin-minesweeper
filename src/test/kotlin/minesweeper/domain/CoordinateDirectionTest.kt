package minesweeper.domain

import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CoordinateDirectionTest {
    @Test
    fun `오른쪽 좌표 값은 x를 1 더한 값이다`() {
        CoordinateDirection.RIGHT.x shouldBe 1
        CoordinateDirection.RIGHT.y shouldBe 0
    }

    @Test
    fun `왼쪽 좌표 값은 x를 1 뺀 값이다`() {
        CoordinateDirection.LEFT.x shouldBe -1
        CoordinateDirection.LEFT.y shouldBe 0
    }

    @Test
    fun `위쪽 좌표 값은 y를 1 더한 값이다`() {
        CoordinateDirection.UP.x shouldBe 0
        CoordinateDirection.UP.y shouldBe 1
    }

    @Test
    fun `아래쪽 좌표 값은 y를 1 뺀 값이다`() {
        CoordinateDirection.DOWN.x shouldBe 0
        CoordinateDirection.DOWN.y shouldBe -1
    }

    @Test
    fun `오른쪽 위의 좌표 값은 x를 1 더하고 y를 1 더한 값이다`() {
        CoordinateDirection.RIGHT_UP.x shouldBe 1
        CoordinateDirection.RIGHT_UP.y shouldBe 1
    }

    @Test
    fun `왼쪽 위의 좌표 값은 x를 1 빼고 y를 1 더한 값이다`() {
        CoordinateDirection.LEFT_UP.x shouldBe -1
        CoordinateDirection.LEFT_UP.y shouldBe 1
    }

    @Test
    fun `오른쪽 아래의 좌표 값은 x를 1 더하고 y를 1 뺀 값이다`() {
        CoordinateDirection.RIGHT_DOWN.x shouldBe 1
        CoordinateDirection.RIGHT_DOWN.y shouldBe -1
    }

    @Test
    fun `왼쪽 아래의 좌표 값은 x를 1 빼고 y를 1 뺀 값이다`() {
        CoordinateDirection.LEFT_DOWN.x shouldBe -1
        CoordinateDirection.LEFT_DOWN.y shouldBe -1
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
