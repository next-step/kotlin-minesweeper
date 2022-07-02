package domain

import domain.vo.Point
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CoordinateTest : StringSpec({

    "x 위치가 1보다 작으면 인스턴스 생성에 실패한다" {
        shouldThrow<IllegalArgumentException> {
            Coordinate(x = Point(0), y = Point(1))
        }
    }

    "y 위치가 1보다 작으면 인스턴스 생성에 실패한다" {
        shouldThrow<IllegalArgumentException> {
            Coordinate(x = Point(1), y = Point(0))
        }
    }

    "좌표를 생성한다" {
        val coordinate = Coordinate(Point(1), Point(2))

        coordinate.x shouldBe Point(1)
        coordinate.y shouldBe Point(2)
    }

    "범위를 벗어나는 좌표를 제외한 주변 좌표목록을 생성한다" {
        val coordinate = Coordinate(Point(1), Point(2))

        coordinate.surroundings shouldBe listOf(
            Coordinate(Point(1), Point(1)),
            Coordinate(Point(2), Point(1)),
            Coordinate(Point(2), Point(2)),
            Coordinate(Point(1), Point(3)),
            Coordinate(Point(2), Point(3)),
        )
    }

    "8개의 주변좌표를 생성한다 " {
        val coordinate = Coordinate(Point(3), Point(3))

        coordinate.surroundings shouldBe listOf(
            Coordinate(Point(2), Point(2)),
            Coordinate(Point(3), Point(2)),
            Coordinate(Point(4), Point(2)),
            Coordinate(Point(2), Point(3)),
            Coordinate(Point(4), Point(3)),
            Coordinate(Point(2), Point(4)),
            Coordinate(Point(3), Point(4)),
            Coordinate(Point(4), Point(4)),
        )
    }
})
