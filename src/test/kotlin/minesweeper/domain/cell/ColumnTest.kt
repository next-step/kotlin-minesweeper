package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class ColumnTest : FunSpec({

    context("init") {
        test("열이 제한값보다 작은 경우 예외가 발생한다.") {
            val exception = shouldThrowExactly<IllegalArgumentException> { Column(-1) }
            exception shouldHaveMessage "열은 0이상 이어야 합니다."
        }
    }
})
