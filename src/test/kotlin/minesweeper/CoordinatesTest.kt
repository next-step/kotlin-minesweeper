package minesweeper

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import minesweeper.domain.Coordinate
import minesweeper.domain.Coordinates

class CoordinatesTest : DescribeSpec({

    describe("coordinatesInArea") {
        context("높이와 너비가 주어진 경우") {
            it("그 안에 존재하는 모든 좌료를 리턴한다.") {
                val coordinates = Coordinates.coordinatesInArea(2, 2)

                coordinates shouldContainAll listOf(
                    Coordinate(0, 0),
                    Coordinate(0, 1),
                    Coordinate(1, 0),
                    Coordinate(1, 1)
                )
            }
        }
    }
})
