package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ColsTest : StringSpec({
    "높이의 값은 0보다 커야 한다" {
        val exception = shouldThrow<IllegalArgumentException> { Cols(-1) }
        exception.message shouldBe "높이는 0보다 커야 합니다."
    }
})
