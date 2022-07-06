package minesweeper.domain.cell

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import minesweeper.Coordinate

internal class CoordinateDirectionTest : FreeSpec({

    "주변 좌표를 얻을 수 있다." {
        // given
        val coordinate = Coordinate(x = 1, y = 1)
        val aroundCoordinates = listOf(
            Coordinate(0, 0), Coordinate(0, 1), Coordinate(0, 2),
            Coordinate(1, 0), Coordinate(1, 2),
            Coordinate(2, 0), Coordinate(2, 1), Coordinate(2, 2),
        )

        // when, then
        CoordinateDirection.aroundCoordinates(coordinate = coordinate) shouldContainExactlyInAnyOrder aroundCoordinates
    }
})
