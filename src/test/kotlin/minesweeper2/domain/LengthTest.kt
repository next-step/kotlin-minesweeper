package minesweeper2.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class LengthTest : FunSpec({

    test("값은 숫자이어야함") {
        shouldThrow<IllegalArgumentException> {
            Length.of("f")
        }

        shouldNotThrow<IllegalArgumentException> {
            Length.of("1")
        }
    }

    test("값은 0보다 커야함") {
        shouldThrow<IllegalArgumentException> {
            Length.of("-1")
        }

        shouldThrow<IllegalArgumentException> {
            Length.of("0")
        }

        shouldNotThrow<IllegalArgumentException> {
            Length.of("1")
        }
    }
})
