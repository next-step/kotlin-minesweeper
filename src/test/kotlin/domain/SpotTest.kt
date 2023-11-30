package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse

class SpotTest : StringSpec({
    "Spot은 초기에 닫힌 상태이다" {
        val spot = Spot()
        spot.isOpen().shouldBeFalse()
    }
})