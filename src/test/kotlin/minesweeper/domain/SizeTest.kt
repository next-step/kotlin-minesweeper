package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

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

    Given("곱하려는 다른 사이즈가 주어지면") {
        val other = Size(6)
        When("사이즈는") {
            val actual = Size(5) * other
            Then("곱한 값 원소로 갖는 사이즈를 반환한다.") {
                actual shouldBe Size(30)
            }
        }
    }

    Given("나누려는 다른 사이즈가 주어지면") {
        val other = Size(5)
        When("사이즈는") {
            val actual = Size(10) / other
            Then("몫에서 1을 더한 값을 원소로 갖는 사이즈를 반환한다.") {
                actual shouldBe Size(3)
            }
        }
    }

    Given("나머지를 계산하려는 다른 사이즈가 주어지면") {
        val other = Size(4)
        When("사이즈는") {
            val actual = Size(7) % other
            Then("나머지에서 1을 더한 값을 원소로 갖는 사이즈를 반환한다.") {
                actual shouldBe Size(4)
            }
        }
    }
})
