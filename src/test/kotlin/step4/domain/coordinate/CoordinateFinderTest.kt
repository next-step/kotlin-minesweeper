package step4.domain.coordinate

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import step4.domain.coordinate.Coordinate
import step4.domain.coordinate.CoordinateFinder
import step4.domain.coordinate.CoordinateFinder.EAST
import step4.domain.coordinate.CoordinateFinder.NORTH
import step4.domain.coordinate.CoordinateFinder.NORTH_SOUTH
import step4.domain.coordinate.CoordinateFinder.NORTH_WEST
import step4.domain.coordinate.CoordinateFinder.SOUTH
import step4.domain.coordinate.CoordinateFinder.SOUTH_EAST
import step4.domain.coordinate.CoordinateFinder.SOUTH_WEST
import step4.domain.coordinate.CoordinateFinder.WEST

class CoordinateFinderTest : FunSpec({

    context("find") {
        forAll(
            row(SOUTH, Coordinate(2, 1)),
            row(SOUTH_WEST, Coordinate(2, 0)),
            row(WEST, Coordinate(1, 0)),
            row(NORTH_WEST, Coordinate(0, 0)),
            row(NORTH, Coordinate(0, 1)),
            row(NORTH_SOUTH, Coordinate(0, 2)),
            row(EAST, Coordinate(1, 2)),
            row(SOUTH_EAST, Coordinate(2, 2)),
        ) { input, expected ->
            test("${input}의 다음 위치는 ${expected}이어야 한다.") {
                val actual = input.find(Coordinate(1, 1))
                actual shouldBe expected
            }
        }
    }

    context("nearCoordinates") {
        test("근처의 좌표를 반환한다.") {
            val coordinate = Coordinate(1, 1)
            val actual = CoordinateFinder.nearCoordinates(coordinate)

            actual shouldContainAll listOf(
                Coordinate(0, 0),
                Coordinate(0, 1),
                Coordinate(0, 2),
                Coordinate(1, 0),
                Coordinate(1, 2),
                Coordinate(2, 0),
                Coordinate(2, 1),
                Coordinate(2, 2),
            )
        }
    }
})
