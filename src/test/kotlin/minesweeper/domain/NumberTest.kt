package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class NumberTest : StringSpec({
    "높이와 넓이, 지뢰 갯수는 숫자이어야한다" {
        shouldThrow<IllegalArgumentException> {
            NumberValue.of("a")
        }
    }
    "높이와 넓이, 지뢰 갯수는 0보다 커야한다" {
        shouldThrow<IllegalArgumentException> {
            NumberValue.of("0")
        }
    }
})
