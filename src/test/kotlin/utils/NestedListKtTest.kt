package utils

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class NestedListKtTest : BehaviorSpec({

    Given("세로 100, 가로 50이 주어졌을 떄") {
        val columSize = 100
        val rowSize = 50

        When("중첩 리스트를 만들면") {
            val nestedList = nestedList(
                columnSize = columSize,
                rowSize = rowSize,
            ) { column, row ->
                column to row
            }

            Then("세로 100인 중첩 리스트가 만들어진다") {
                nestedList.size shouldBe columSize
            }

            Then("가로 50인 중첩 리스트가 만들어진다") {
                nestedList.first().size shouldBe rowSize
            }

            Then("100 * 50 리스트가 만들어진다") {
                val expected = List(columSize) { column ->
                    List(rowSize) { row ->
                        column to row
                    }
                }
                nestedList shouldBe expected
            }
        }
    }
})
