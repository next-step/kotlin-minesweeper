package domain.map

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class CoordinateTest : StringSpec({

    "x값에 음수가 입력되면 RuntimeException 예외처리를 한다" {
        shouldThrow<RuntimeException> {
            Coordinate(x = -1, y = 0)
        }
    }

    "y값에 음수가 입력되면 RuntimeException 예외처리를 한다" {
        shouldThrow<RuntimeException> {
            Coordinate(x = 0, y = -1)
        }
    }
})
