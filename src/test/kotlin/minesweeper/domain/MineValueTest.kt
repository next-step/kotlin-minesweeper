package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineValueTest : StringSpec({
    "지뢰의 수는 0보다 커야 한다" {
        val exception = shouldThrow<IllegalArgumentException> { MineValue(-1, 10, 10) }
        exception.message shouldBe "지뢰의 수는 0보다 커야 합니다."
    }

    "지뢰의 수는 높이와 너비의 콥보다 작아야 한다" {
        val exception = shouldThrow<IllegalArgumentException> { MineValue(100, 10, 10) }
        exception.message shouldBe "지뢰의 수는 높이와 너비의 곱보다 작아야 합니다."
    }
})
