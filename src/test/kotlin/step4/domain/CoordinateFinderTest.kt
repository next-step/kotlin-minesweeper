package step4.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import step4.domain.CoordinateFinder.EAST
import step4.domain.CoordinateFinder.NORTH
import step4.domain.CoordinateFinder.NORTH_SOUTH
import step4.domain.CoordinateFinder.NORTH_WEST
import step4.domain.CoordinateFinder.SOUTH
import step4.domain.CoordinateFinder.SOUTH_EAST
import step4.domain.CoordinateFinder.SOUTH_WEST
import step4.domain.CoordinateFinder.WEST

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
})
