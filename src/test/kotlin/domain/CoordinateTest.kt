package domain

import domain.vo.Point
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

internal class CoordinateTest : FreeSpec({

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

    "isAdjacentTo" - {
        "근접한 좌표를 제공하면 true 를 반환한다" {
            val coordinate = Coordinate(Point(3), Point(3))
            val surrounds = listOf(
                Coordinate(Point(2), Point(2)),
                Coordinate(Point(3), Point(2)),
                Coordinate(Point(4), Point(2)),
                Coordinate(Point(2), Point(3)),
                Coordinate(Point(4), Point(3)),
                Coordinate(Point(2), Point(4)),
                Coordinate(Point(3), Point(4)),
                Coordinate(Point(4), Point(4)),
            )

            surrounds.forAll {
                it.isAdjacentTo(coordinate).shouldBeTrue()
            }
        }

        "근접하지 않은 좌표를 제공하면 false 를 반환한다" {
            val coordinate = Coordinate(Point(3), Point(4))

            coordinate.isAdjacentTo(Coordinate(Point(3), Point(6))).shouldBeFalse()
        }

        "동일한 좌표를 제공하면 false 를 반환한다" {
            val coordinate = Coordinate(Point(3), Point(4))

            coordinate.isAdjacentTo(Coordinate(Point(3), Point(4))).shouldBeFalse()
        }
    }
})
