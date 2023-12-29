package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly

class PositionTest : BehaviorSpec({
    Given("주변 8개의 위치를 알고 싶은 위치가 주어지면") {
        val position = Position(2, 2)
        When("getAround 함수는") {
            val aroundPositions = position.getAround()
            Then("주변 8개의 위치를 반환한다.") {
                aroundPositions shouldContainExactly listOf(
                    Position(1, 1),
                    Position(1, 2),
                    Position(1, 3),
                    Position(2, 1),
                    Position(2, 3),
                    Position(3, 1),
                    Position(3, 2),
                    Position(3, 3),
                )
            }
        }
    }

    Given("인접한 상하좌우의 위치를 알고 싶은 위치가 주어지면") {
        val position = Position(2, 2)
        When("getAdjacent 함수는") {
            val aroundPositions = position.getAdjacent()
            Then("인접한 상하좌우의 위치를 반환한다.") {
                aroundPositions shouldContainExactly listOf(
                    Position(1, 2),
                    Position(2, 1),
                    Position(2, 3),
                    Position(3, 2),
                )
            }
        }
    }
})
