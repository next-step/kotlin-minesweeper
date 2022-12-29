package util

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class ParserTest : BehaviorSpec({
    Given("정상적인 입력이 들어온다면, ") {
        When("파싱을 진행할 때, ") {
            val expression = "1, 2, 3"
            val result = Parser.parse(expression)
            Then("성공한다.") {
                result shouldBe listOf(1, 2, 3)
            }
        }
    }
})
