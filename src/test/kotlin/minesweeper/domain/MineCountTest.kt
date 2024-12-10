package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

class MineCountTest : BehaviorSpec({
    Given("높이는") {
        When("인자가 음수면") {
            val count = -1
            Then("생성 시 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> { MineCount(count) }
            }
        }
    }
})
