package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class HeightTest : BehaviorSpec({

    given("높이의 값 10으로 주어지면") {
        val value = 10
        When("높이를 생성할 때") {
            val height = Height(value)
            Then("높이값은 10이다") {
                height.value shouldBe value
            }
        }
    }

    given("높이의 값이 0으로 주어지면") {
        val value = 0
        When("높이를 생성할 때") {
            val exception = shouldThrow<IllegalArgumentException> {
                Height(value)
            }
            Then("IllegalArgumentException이 발생한다") {
                exception.message shouldBe "높이는 1이상이여야 합니다."
            }
        }
    }
})
