package minesweeper.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll

class CoordinateTest : DescribeSpec({

    describe("constructor") {
        context("높이와 너비가 주어지면") {
            it("0부터 높이와 너비 만큼 좌표를 생성한다") {
                val coordinates = Coordinate.listOf(2, 2)

                coordinates shouldContainAll listOf(
                    Coordinate(0, 0),
                    Coordinate(1, 0),
                    Coordinate(0, 1),
                    Coordinate(1, 1),
                )
            }
        }
    }

    describe("nearCoordinate") {
        it("주변 8개의 좌표들을 구할 수 있다.") {
            val coordinate = Coordinate(2, 2)

            coordinate.nearCoordinate() shouldContainAll listOf(
                Coordinate(1, 1),
                Coordinate(1, 2),
                Coordinate(1, 3),
                Coordinate(2, 1),
                Coordinate(2, 3),
                Coordinate(3, 1),
                Coordinate(3, 2),
                Coordinate(3, 3),
            )
        }
    }
})
