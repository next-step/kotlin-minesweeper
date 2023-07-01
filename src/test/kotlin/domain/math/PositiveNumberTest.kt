package domain.math

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PositiveNumberTest : StringSpec({

    "0 입력 시 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> { PositiveNumber(0) }
    }

    "음수 입력 시 RuntimeException 예외 처리를 한다" {
        shouldThrow<RuntimeException> { PositiveNumber(-1) }
    }
})
