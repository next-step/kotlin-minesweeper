package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class FieldsTest : BehaviorSpec({
    Given("주어진 Fields에 ") {
        val firstLand = Coordinate(0, 0)
        val secondLand = Coordinate(1, 1)
        val firstFields = Fields(mapOf(firstLand to Land(), secondLand to Land()))

        When("좌표에 해당하는 Field가 있다면 ") {
            val field = firstFields.getField(firstLand)

            Then("반환한다.") {
                field::class shouldBe Land::class
            }
        }

        When("좌표에 해당하는 Field가 없다면 ") {
            Then("예외를 던진다.") {
                shouldThrow<IllegalArgumentException> {
                    firstFields.getField(Coordinate(0, 1))
                }
            }
        }

        val secondCoordinate = Coordinate(0, 1)
        When("존재하는 인근 좌표들을 ") {
            val nearByFields = firstFields.getNearByFields(secondCoordinate)
            Then("가져온다.") {
                nearByFields.size shouldBe 2
            }
        }

        val thirdCoordinate = Coordinate(2, 2)
        When("존재하는 인근 좌표들이 없다면 ") {
            val nearByFields = firstFields.getNearByFields(thirdCoordinate)
            Then("제외하고 가져온다.") {
                nearByFields.size shouldBe 1
            }
        }

        val secondFields = Fields(mapOf(firstLand to Land(true), secondLand to Land(false)))
        When("모두 열려 있지 않다면 ") {
            val isLandAllOpened = secondFields.isLandAllOpened()
            Then("false를 반환한다.") {
                isLandAllOpened shouldBe false
            }
        }

        val thirdFields = Fields(mapOf(firstLand to Land(true), secondLand to Land(true)))
        When("모두 열려있다면 ") {
            val isLandAllOpened = thirdFields.isLandAllOpened()
            Then("true를 반환한다.") {
                isLandAllOpened shouldBe true
            }
        }

        val fourthFields = Fields(mapOf(firstLand to Land(false), secondLand to Mine()))
        When("땅을 연다면 ") {
            fourthFields.open(firstLand)
            Then("열린다.") {
                val field = fourthFields.getField(firstLand) as Land
                field.isOpened shouldBe true
            }
        }

        When("지뢰를 연다면 ") {
            Then("열린다.") {
                shouldThrow<IllegalStateException> {
                    fourthFields.open(secondLand)
                }
            }
        }
    }
})
