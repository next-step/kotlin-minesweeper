package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineCountTest : BehaviorSpec({
    Given("MineCount 객체를 생성할 때") {

        When("지뢰 개수가 0보다 큰 값인 경우") {
            Then("정상적으로 객체가 생성된다") {
                val mineCount = MineCount(5)
                mineCount.value shouldBe 5
            }
        }

        When("지뢰 개수가 0인 경우") {
            Then("예외가 발생한다") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        MineCount(0)
                    }
                exception.message shouldBe "지뢰 개수는 0보다 작을 수 없습니다."
            }
        }

        When("지뢰 개수가 음수인 경우") {
            Then("예외가 발생한다") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        MineCount(-1)
                    }
                exception.message shouldBe "지뢰 개수는 0보다 작을 수 없습니다."
            }
        }
    }
})
