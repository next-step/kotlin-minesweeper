package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
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
})
