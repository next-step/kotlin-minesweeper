package step4.domain.state

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.throwable.shouldHaveMessage
import step4.domain.Cell
import step4.domain.Cells

class RunningTest : FunSpec({

    context("installMine") {
        test("지뢰를 설치하려하면 예외가 발생한다.") {
            val running = Running(0, Cells(mapOf()))
            val exception = shouldThrowExactly<IllegalStateException> { running.installMine(1) { Cell() } }
            exception shouldHaveMessage "지뢰를 설치할 수 있는 상태가 아닙니다."
        }
    }
})
