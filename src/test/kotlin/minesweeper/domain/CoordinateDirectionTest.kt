package minesweeper.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.data.row
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

import minesweeper.Coordinate

internal class CoordinateDirectionTest : FreeSpec({

    "주어진 좌표의 주변 8방향 좌표를 얻을 수 있다." {
        // given
        listOf(
            row(
                Coordinate(x = 0, y = 0),
                listOf(
                    Coordinate(-1, -1),
                    Coordinate(-1, 0),
                    Coordinate(-1, 1),
                    Coordinate(0, -1),
                    Coordinate(0, 1),
                    Coordinate(1, -1),
                    Coordinate(1, 0),
                    Coordinate(1, 1)
                )
            ),
            row(
                Coordinate(x = 1, y = 1),
                listOf(
                    Coordinate(0, 0),
                    Coordinate(0, 1),
                    Coordinate(0, 2),
                    Coordinate(1, 0),
                    Coordinate(1, 2),
                    Coordinate(2, 0),
                    Coordinate(2, 1),
                    Coordinate(2, 2)
                )
            )
        ).forAll { (coordinate, aroundCoordinates) ->
            CoordinateDirection.around(coordinate = coordinate) shouldContainExactlyInAnyOrder aroundCoordinates
        }
    }
})
