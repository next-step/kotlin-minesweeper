package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class RowTest : FunSpec({

    context("init") {
        test("너비가 제한값보다 작은 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Row(-1) }
            exception shouldHaveMessage "행은 0이상 이어야 합니다."
        }
    }
})
