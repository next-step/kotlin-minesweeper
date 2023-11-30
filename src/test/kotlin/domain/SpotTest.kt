package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class SpotTest : StringSpec({
    "Spot은 초기에 닫힌 상태이다" {
        val spot = Spot()
        spot.isOpen().shouldBeFalse()
    }

    "Spot을 열면 open 상태가 된다" {
        val spot = Spot()
        spot.open()
        spot.isOpen().shouldBeTrue()
    }
})