package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PositiveNumberTest : StringSpec({
    "양수여야 한다." {
        shouldNotThrowAny {
            PositiveNumber(1)
        }
    }

    "0이면 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            PositiveNumber(0)
        }
    }

    "음수이면 예외를 던진다." {
        shouldThrow<IllegalArgumentException> {
            PositiveNumber(-1)
        }
    }

    "곱하기를 수행할 수 있다." {
        val result = PositiveNumber(2) * PositiveNumber(3)
        result shouldBe PositiveNumber(6)
    }
})
