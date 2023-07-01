package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec

class HeightTest : FunSpec( {

    test("높이는 숫자이어야함") {
        shouldThrow<IllegalArgumentException> {
            Height.of("f")
        }

        shouldNotThrow<IllegalArgumentException> {
            Height.of("1")
        }
    }

    test("높이는 0보다 커야함") {
        shouldThrow<IllegalArgumentException> {
            Height.of("-1")
        }

        shouldThrow<IllegalArgumentException> {
            Height.of("0")
        }

        shouldNotThrow<IllegalArgumentException> {
            Height.of("1")
        }
    }
})
