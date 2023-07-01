package minesweeper

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class WidthTest : FunSpec({

    context("init") {
        test("너비가 제한값보다 작은 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Width(0) }
            exception shouldHaveMessage "너비는 0보다 커야 합니다."
        }
    }
})
