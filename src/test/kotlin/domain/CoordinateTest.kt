package domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll

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
                nearByCoordinates shouldContainAll expectedNearByCoordinates
            }
        }
    }
})
