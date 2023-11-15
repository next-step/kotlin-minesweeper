package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class WidthTest : BehaviorSpec({

    given("너비의 값이 10이 주어지면") {
        val value = 10
        When("너비를 생성할 때") {
            val width = Width(value)
            Then("너비값은 10이다") {
                width.value shouldBe value
            }
        }
    }

    given("너비의 값이 0이 주어지면") {
        val value = 0
        When("너비를 생성할 때") {
            val exception = shouldThrow<IllegalArgumentException> {
                Width(value)
            }
            Then("IllegalArgumentException이 발생한다") {
                exception.message shouldBe "너비는 1이상이여야 합니다."
            }
        }
    }
})
