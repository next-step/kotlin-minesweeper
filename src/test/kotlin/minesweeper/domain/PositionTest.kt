package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.throwable.shouldHaveMessage

class PositionTest : StringSpec({
    "좌표는 음의 숫자일 경우 예외가 발생한다." {
        val message = shouldThrow<IllegalArgumentException> {
            Position(-1)
        }

        message shouldHaveMessage "0 보다 같거나 큰 수이여야 합니다."
    }
})
