package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class MapElementTest : BehaviorSpec({
    forAll(
        table(
            headers("value"),
            row(-1),
            row(9),
        ),
    ) { value ->
        Given("${value}가 주어졌다") {
            When("해당 숫자에 해당하는 map element를 구하면") {
                Then("예외가 던져진다") {
                    shouldThrow<IllegalArgumentException> { NumberMapElement(value) }
                }
            }
        }
    }

    forAll(
        table(
            headers("value"),
            row(0),
            row(1),
            row(2),
            row(3),
            row(4),
            row(5),
            row(6),
            row(7),
            row(8),
        ),
    ) { value ->
        Given("${value}가 주어졌다") {
            When("해당 숫자에 해당하는 map element를 구하면") {
                Then("예외가 던져지지 않는다") {
                    shouldNotThrowAny { NumberMapElement(value) }
                }
            }
        }

        Given("NumberMapElement($value)가 주어졌다") {
            val numberMapElement = NumberMapElement(value)
            When("해당 MapElement의 가려짐 여부를 확인하면") {
                Then("가려져있다고 나온다") {
                    numberMapElement.isCovered() shouldBe true
                }
            }

            When("MapElement를 나타나게 하면") {
                numberMapElement.reveal()
                Then("MapElement가 드러난다") {
                    numberMapElement.isCovered() shouldBe false
                }
            }
        }
    }

    Given("MineMapElement가 주어졌다") {
        val mineMapElement = MineMapElement()
        When("해당 MapElement의 가려짐 여부를 확인하면") {
            Then("가려져있다고 나온다") {
                mineMapElement.isCovered() shouldBe true
            }
        }

        When("MapElement를 나타나게 하면") {
            mineMapElement.reveal()
            Then("MapElement가 드러난다") {
                mineMapElement.isCovered() shouldBe false
            }
        }
    }
})
