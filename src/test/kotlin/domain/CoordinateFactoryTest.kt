package domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec

internal class CoordinateFactoryTest : BehaviorSpec({
    Given("좌표를 생성하면서 높이와 너비도 생성자에 같이 넣어줄 때") {
        When("높이와 너비보다 작다면 ") {
            Then("정상적으로 생성된다.") {
                shouldNotThrowAny {
                    CoordinateFactory.create(0, 0, Height(5), Width(5))
                }
            }
        }

        When("높이와 너비보다 작지 않으면 ") {
            Then("예외를 던진다.") {
                shouldThrow<IllegalArgumentException> {
                    CoordinateFactory.create(7, 7, Height(5), Width(5))
                }
            }
        }
    }
})
