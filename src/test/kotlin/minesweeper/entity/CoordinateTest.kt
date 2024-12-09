package minesweeper.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CoordinateTest : BehaviorSpec({
    Given("Coordinate 객체를 생성할 때") {
        When("x와 y가 모두 0 이상의 값인 경우") {
            Then("Coordinate 객체가 정상적으로 생성된다") {
                val coordinate = Coordinate(5, 10)
                coordinate.x shouldBe 5
                coordinate.y shouldBe 10
            }
        }

        When("x가 음수인 값인 경우") {
            Then("예외가 발생한다") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Coordinate(-1, 10)
                    }
                exception.message shouldBe "x는 0보다 커야합니다."
            }
        }

        When("y가 음수인 값인 경우") {
            Then("예외가 발생한다") {
                val exception =
                    shouldThrow<IllegalArgumentException> {
                        Coordinate(10, -1)
                    }
                exception.message shouldBe "y는 0보다 커야합니다."
            }
        }
    }
})
