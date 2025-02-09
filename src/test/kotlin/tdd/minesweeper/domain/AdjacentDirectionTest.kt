package tdd.minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class AdjacentDirectionTest : BehaviorSpec({
    given("위치를 받아") {
        val location = Location(row = 2, col = 2)

        `when`("인접 위치들을 모두 구하면") {
            val result = AdjacentDirection.allAdjacentLocations(location)

            then("8방향의 인접위치들을 반환한다") {
                result shouldBe
                    listOf(
                        Location(row = 1, col = 1),
                        Location(row = 1, col = 2),
                        Location(row = 1, col = 3),
                        Location(row = 2, col = 1),
                        Location(row = 2, col = 3),
                        Location(row = 3, col = 1),
                        Location(row = 3, col = 2),
                        Location(row = 3, col = 3),
                    )
            }
        }
    }
})
