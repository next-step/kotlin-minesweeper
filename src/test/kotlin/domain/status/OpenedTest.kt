package domain.status

import domain.Spot
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.shouldBe

class OpenedTest : StringSpec({
    "지뢰를 밟은 상태는 *를 표시한다" {
        Opened.Mine.getSymbol() shouldBe "*"
    }

    "지뢰를 밟은 상태는 지뢰가 맞다" {
        Opened.Mine.mineTrapped() shouldBe true
    }

    "지뢰가 없는 상태는 주변 지뢰 개수를 표시한다" {
        Spot.VALIDATE_NEAR_MINE_COUNT.forEach { count ->
            val status = Opened.from(count, false)
            status.getSymbol() shouldBe count.toString()
            status.mineTrapped().shouldBeFalse()
        }
    }
})
