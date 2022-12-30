package domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

internal class CoordinateTest : BehaviorSpec({
    Given("좌표가 주어졌을 때, ") {
        val coordinate = Coordinate(1, 1)

        When("인근 좌표를 구하면 ") {
            val nearByCoordinates = coordinate.getNearByCoordinates()

            Then("정상적으로 가져온다.") {
                val expectedNearByCoordinates = listOf(
                    Coordinate(0, 0),
                    Coordinate(0, 1),
                    Coordinate(0, 2),
                    Coordinate(1, 0),
                    Coordinate(1, 2),
                    Coordinate(2, 0),
                    Coordinate(2, 1),
                    Coordinate(2, 2),
                )
                nearByCoordinates shouldBe expectedNearByCoordinates
            }
        }
    }

    Given("좌표를 생성하면서 높이와 너비도 생성자에 같이 넣어줄 때") {
        When("높이와 너비보다 작다면 ") {
            Then("정상적으로 생성된다.") {
                shouldNotThrowAny {
                    Coordinate(0, 0, Height(5), Width(5))
                }
            }
        }

        When("높이와 너비보다 작지 않으면 ") {
            Then("예외를 던진다.") {
                shouldThrow<IllegalArgumentException> {
                    Coordinate(7, 7, Height(5), Width(5))
                }
            }
        }
    }
})
