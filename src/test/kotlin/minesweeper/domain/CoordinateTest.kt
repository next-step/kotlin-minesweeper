package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll

class CoordinateTest : DescribeSpec({

    describe("constructor") {
        context("높이와 너비가 주어지면") {
            it("0부터 높이와 너비 만큼 좌표를 생성한다") {
                val coordinates = Coordinate.listOf(2, 2)

                coordinates shouldContainAll listOf(
                    Coordinate(CoordinateIndex(0), CoordinateIndex(0)),
                    Coordinate(CoordinateIndex(1), CoordinateIndex(0)),
                    Coordinate(CoordinateIndex(0), CoordinateIndex(1)),
                    Coordinate(CoordinateIndex(1), CoordinateIndex(1)),
                )
            }
        }
    }
})
