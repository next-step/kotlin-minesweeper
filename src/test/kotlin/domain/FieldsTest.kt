package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class FieldsTest : BehaviorSpec({
    Given("주어진 Fields에 ") {
        val firstLand = Coordinate(0, 0)
        val secondLand = Coordinate(1, 1)
        val fields = Fields(mapOf(firstLand to Land(), secondLand to Land()))

        When("좌표에 해당하는 Field가 있다면 ") {
            val field = fields.getField(firstLand)

            Then("반환한다.") {
                field::class shouldBe Land::class
            }
        }

        When("좌표에 해당하는 Field가 없다면 ") {
            Then("예외를 던진다.") {
                shouldThrow<IllegalArgumentException> {
                    fields.getField(Coordinate(0, 1))
                }
            }
        }

        val secondCoordinate = Coordinate(0, 1)
        When("존재하는 인근 좌표들을 ") {
            val nearByFields = fields.getNearByFields(secondCoordinate)
            Then("가져온다.") {
                nearByFields.size shouldBe 2
            }
        }

        val thirdCoordinate = Coordinate(2, 2)
        When("존재하는 인근 좌표들이 없다면 ") {
            val nearByFields = fields.getNearByFields(thirdCoordinate)
            Then("제외하고 가져온다.") {
                nearByFields.size shouldBe 1
            }
        }
    }
})
