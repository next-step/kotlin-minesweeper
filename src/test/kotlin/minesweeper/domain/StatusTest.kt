package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class StatusTest : StringSpec({
    "상태 변화가 불가능한 행동일 경우 예외가 발생한다." {
        forAll(
            row(Status.WINNING),
            row(Status.LOSING),
        ) { status ->
            val message = shouldThrow<IllegalStateException> {
                status.next(EVENT.LOSE)
            }

            message shouldHaveMessage "다음 상태로 변경이 불가능 합니다."
        }
    }

    "진행 중 상태에서 다른 상태로 변경 하다." {
        forAll(
            row(EVENT.WIN),
            row(EVENT.LOSE),
        ) { event ->
            val nextState = Status.PROCESSING.next(event)
            nextState shouldBe event.nextState()
        }
    }
})
