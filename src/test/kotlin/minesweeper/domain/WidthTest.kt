package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class WidthTest : FunSpec( {

    test("너비는 숫자이어야함") {
        shouldThrow<IllegalArgumentException> {
            Width.of("f")
        }

        shouldNotThrow<IllegalArgumentException> {
            Width.of("1")
        }
    }

    test("너비는 0보다 커야함") {
        shouldThrow<IllegalArgumentException> {
            Width.of("-1")
        }

        shouldThrow<IllegalArgumentException> {
            Width.of("0")
        }

        shouldNotThrow<IllegalArgumentException> {
            Width.of("1")
        }
    }
})
