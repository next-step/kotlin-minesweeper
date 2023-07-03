package domain.map

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CoordinateTest : StringSpec({

    "x값에 음수가 입력되면 RuntimeException 예외처리를 한다" {
        shouldThrow<RuntimeException> {
            Coordinate(x = -1, y = 0)
        }
    }

    "y값에 음수가 입력되면 RuntimeException 예외처리를 한다" {
        shouldThrow<RuntimeException> {
            Coordinate(x = 0, y = -1)
        }
    }

    "(0, 0) 주변의 좌표는 3개이다" {
        val aroundCoordinates = Coordinate(0, 0).aroundCoordinates()
        val expected = setOf(
            Coordinate(0, 1), // bottom
            Coordinate(1, 1), // right bottom
            Coordinate(1, 0) // right
        )
        aroundCoordinates.intersect(expected).size shouldBe expected.size
    }

    "(Int.MAX_VALUE, 0) 주변의 좌표는 3개이다" {
        val aroundCoordinates = Coordinate(Int.MAX_VALUE, 0).aroundCoordinates()
        val expected = setOf(
            Coordinate(Int.MAX_VALUE - 1, 0), // left
            Coordinate(Int.MAX_VALUE - 1, 1), // left bottom
            Coordinate(Int.MAX_VALUE - 1, 1), // bottom
        )
        aroundCoordinates.intersect(expected).size shouldBe expected.size
    }

    "(Int.MAX_VALUE, Int.MAX_VALUE) 주변의 좌표는 3개이다" {
        val aroundCoordinates = Coordinate(Int.MAX_VALUE, Int.MAX_VALUE).aroundCoordinates()
        val expected = setOf(
            Coordinate(Int.MAX_VALUE - 1, Int.MAX_VALUE), // left
            Coordinate(Int.MAX_VALUE - 1, Int.MAX_VALUE - 1), // left top
            Coordinate(Int.MAX_VALUE, Int.MAX_VALUE - 1), // top
        )
        aroundCoordinates.intersect(expected).size shouldBe expected.size
    }

    "(0, Int.MAX_VALUE) 주변의 좌표는 3개이다" {
        val aroundCoordinates = Coordinate(0, Int.MAX_VALUE).aroundCoordinates()
        val expected = setOf(
            Coordinate(1, Int.MAX_VALUE), // right
            Coordinate(1, Int.MAX_VALUE - 1), // right top
            Coordinate(0, Int.MAX_VALUE - 1), // top
        )
        aroundCoordinates.intersect(expected).size shouldBe expected.size
    }

    "(1, 1)의 주변 좌표는 8개이다" {
        val aroundCoordinates = Coordinate(1, 1).aroundCoordinates()
        val expected = setOf(
            Coordinate(0, 1), // left
            Coordinate(0, 0), // left top
            Coordinate(1, 0), // top
            Coordinate(2, 0), // right top
            Coordinate(2, 1), // right
            Coordinate(2, 2), // right bottom
            Coordinate(1, 2), // bottom
            Coordinate(0, 2), // left bottom
        )
        aroundCoordinates.intersect(expected).size shouldBe expected.size
    }
})
