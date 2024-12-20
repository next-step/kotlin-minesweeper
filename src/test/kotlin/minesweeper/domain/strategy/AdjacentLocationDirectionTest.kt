package minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import minesweeper.domain.cell.Location

class AdjacentLocationDirectionTest : BehaviorSpec({
    given("row = 2, column = 2의 위치를 받아") {
        val location = Location(row = 2, column = 2)

        `when`("모든 인접 위치를 구하면") {
            val result = AdjacentLocationDirection.allAdjacentLocations(location)

            then("인접 방향 8칸의 위치 리스트를 받을 수 있다") {
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
