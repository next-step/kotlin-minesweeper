package domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class LandTest : BehaviorSpec({
    Given("땅이 주어졌을 때 ") {
        val firstLand = Land(isOpened = false)
        When("열리지 않았다면 ") {
            firstLand.open()
            Then("열린다.") {
                firstLand.isOpened shouldBe true
            }
        }
    }
})
