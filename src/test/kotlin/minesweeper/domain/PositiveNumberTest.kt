package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import minesweeper.domain.PositiveNumber.Companion.asPositiveNumber

class PositiveNumberTest : FreeSpec({

    "0 또는 0보다 작을 경우 예외를 발생한다." - {
        withData(
            0, -1, -10, -2
        ) { number ->
            val throws = shouldThrow<IllegalArgumentException> { number.asPositiveNumber() }
            throws.message shouldBe "0 보다 큰 값만 사용할 수 있습니다."
        }
    }
})
