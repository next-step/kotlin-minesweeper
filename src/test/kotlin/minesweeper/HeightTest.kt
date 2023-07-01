package minesweeper

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class HeightTest : FunSpec({

    context("init") {
        test("높이가 제한값보다 작은 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Height(0) }
            exception shouldHaveMessage "높이는 0보다 커야 합니다."
        }
    }
})
