package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import minesweeper.domain.cell.Location

class AdjacentLocationDirectionTest : BehaviorSpec({
    given("AdjacentLocationDirection 은") {
        val location = Location(row = 2, column = 2)

        `when`("하나의 Location 을 받아 allAdjacentLocations()을 호출하면") {
            val result = AdjacentLocationDirection.allAdjacentLocations(location)

            then("인접한 8칸의 위치 리스트를 반환한다") {
                result shouldContainExactlyInAnyOrder
                    listOf(
                        Location(row = 1, column = 1),
                        Location(row = 1, column = 2),
                        Location(row = 1, column = 3),
                        Location(row = 2, column = 1),
                        Location(row = 2, column = 3),
                        Location(row = 3, column = 1),
                        Location(row = 3, column = 2),
                        Location(row = 3, column = 3),
                    )
            }
        }
    }
})
