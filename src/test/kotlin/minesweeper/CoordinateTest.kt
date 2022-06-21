package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import minesweeper.domain.Coordinate

class CoordinateTest : DescribeSpec({

    describe("construct") {
        context("주어진 x, y 좌표값이 음수인 경우") {
            it("IllegalArgumentException 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    Coordinate(-1, 0)
                }
            }
        }
    }

    describe("coordinatesInArea") {
        context("높이와 너비가 주어진 경우") {
            it("그 안에 존재하는 모든 좌료를 리턴한다.") {
                val coordinates = Coordinate.coordinatesInArea(2, 2)

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
