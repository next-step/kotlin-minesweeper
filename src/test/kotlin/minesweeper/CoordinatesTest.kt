package minesweeper

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import minesweeper.domain.Area
import minesweeper.domain.Coordinate
import minesweeper.domain.Coordinates

class CoordinatesTest : DescribeSpec({

    describe("coordinatesInArea") {
        context("높이와 너비가 주어진 경우") {
            it("그 안에 존재하는 모든 좌료를 리턴한다.") {
                val coordinates = Coordinates.coordinatesInArea(Area(2, 2))

                coordinates shouldContainAll listOf(
                    Coordinate(0, 0),
                    Coordinate(0, 1),
                    Coordinate(1, 0),
                    Coordinate(1, 1)
                )
            }
        }
    }

    describe("containsCount") {
        context("Coordinate(0,0), Coordinate(0,1) 을 가지고 있을때 ") {
            it("Coordinate(0,1) 를 가진 Coordinates 가 입력으로 들어오면 1을 리턴한다.") {
                val coordinates = Coordinates(setOf(Coordinate(0, 0), Coordinate(0, 1)))
                val otherCoordinates = Coordinates(setOf(Coordinate(0, 1)))

                coordinates.containsCount(otherCoordinates) shouldBe 1
            }
        }
    }
})
