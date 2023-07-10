package minesweeper.domain.vo

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainAll

class PositionTest : BehaviorSpec({
    given("위치 (1,1)") {
        val position = Position.of(1, 1)

        `when`("xMax =3, yMax=3 인 neighbor를 구하면") {
            val neighbors = position.getNeighbors(xLimit = 3, yLimit = 3)

            then("8개를 리턴한다.") {
                neighbors shouldContainAll listOf(
                    Position.of(0, 0),
                    Position.of(0, 1),
                    Position.of(1, 0),
                    Position.of(2, 0),
                    Position.of(0, 2),
                    Position.of(2, 2),
                    Position.of(2, 1),
                    Position.of(1, 2),
                )
            }
        }

        `when`("xMax =1, yMax=1 인 neighbor를 구하면") {
            val neighbors = position.getNeighbors(xLimit = 1, yLimit = 1)

            then("1개를 리턴한다.") {
                neighbors shouldContainAll listOf(
                    Position.of(0, 0),
                )
            }
        }
    }

    given("위치 (0,0)") {
        val position = Position.of(0, 0)

        `when`("xMax =2, yMax=2 인 neighbor를 구하면") {
            val neighbors = position.getNeighbors(xLimit = 2, yLimit = 2)

            then("주변 위치 3개를 리턴한다.") {
                neighbors shouldContainAll listOf(
                    Position.of(1, 0),
                    Position.of(0, 1),
                    Position.of(1, 1)
                )
            }
        }
    }
})
