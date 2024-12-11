package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class HeightTest : BehaviorSpec({
    Given("높이는") {
        When("값이 음수면") {
            val value = -1
            Then("생성 시 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> { Height(value) }
            }
        }

        When("값이 양수면") {
            val value = 1
            Then("예외 없이 생성된다.") {
                shouldNotThrow<IllegalArgumentException> { Height(value) }
            }
        }
    }
})
