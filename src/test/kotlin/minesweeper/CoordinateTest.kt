package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Coordinate
import minesweeper.domain.Coordinates

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

    describe("aroundCoordinates") {
        context("x=1,y=1 좌표를 가질 경우") {
            it("그 주변 좌표들을 리턴한다.") {
                val expected = Coordinates(
                    setOf(
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

                Coordinate(1, 1).aroundCoordinates() shouldBe expected
            }
        }
    }
})
