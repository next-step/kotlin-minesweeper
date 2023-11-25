package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.datatest.withData
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

class CoordinateTest : FunSpec({
    context("좌표의 x, y 값이 음수이면 예외가 발생한다") {
        withData(
            row(0, -1),
            row(-1, 0),
            row(-1, -1)
        ) { (x, y) ->
            shouldThrow<IllegalArgumentException> { Coordinate(x = x, y = y) }
        }
    }

    context("좌표는 y값이 클수록, y값이 같다면, x값이 클수록 크다.") {
        withData(
            row(0, 1, 0, 0),
            row(1, 0, 0, 0),
            row(0, 1, 1, 0)
        ) { (x1, y1, x2, y2) ->
            Coordinate(x = x1, y = y1) shouldBeGreaterThan Coordinate(x = x2, y = y2)
        }
    }

    test("동일한 x, y를 갖는 좌표는 같은 좌표다") {
        Coordinate(0, 0) shouldBe Coordinate(0, 0)
    }
})
