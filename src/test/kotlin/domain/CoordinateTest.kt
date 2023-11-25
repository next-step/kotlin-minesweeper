package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.datatest.withData

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
})
