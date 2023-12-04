package domain.status

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ClosedTest : StringSpec({
    "닫힌 상태는 X를 표시한다" {
        Closed.getSymbol() shouldBe "X"
    }

    "닫힌 상태는 지뢰가 아니다" {
        Closed.mineTrapped() shouldBe false
    }
})
