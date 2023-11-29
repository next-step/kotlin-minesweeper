package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row

class SizeTest : BehaviorSpec({
    Given("1 이상 100 이하의 숫자가 주어지면") {
        When("사이즈는") {
            Then("주어진 숫자를 가지는 사이즈를 정상적으로 생성한다.") {
                forAll(
                    row(1),
                    row(2),
                    row(99),
                    row(100),
                ) { value ->
                    shouldNotThrowAny {
                        Size(value)
                    }
                }
            }
        }
    }

    Given("0 이하 101 이상의 숫자가 주어지면") {
        When("사이즈는") {
            Then("에러를 반환한다.") {
                forAll(
                    row(-1),
                    row(0),
                    row(101),
                    row(102),
                ) { value ->
                    shouldThrow<IllegalArgumentException> {
                        Size(value)
                    }
                }
            }
        }
    }
})
