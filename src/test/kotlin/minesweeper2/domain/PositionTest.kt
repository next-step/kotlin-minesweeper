package minesweeper2.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PositionTest : StringSpec({
    "위치 값은 0보다 커야함" {
        shouldThrow<IllegalArgumentException> {
            Position(-1)
        }
    }

    "위치 값은 숫자이어야 함" {
        shouldThrow<IllegalArgumentException> {
            Position.of("a")
        }
    }
})