package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PositionTest : BehaviorSpec({

    given("x좌표 10, y좌표 10을 가진 위치가 주어지면") {
        val position = Position(10, 10)
        When("x좌표 10, y좌표 9을 가진 위치와 비교하면") {
            val result = position.match(Position(10, 9))
            Then("false를 반환한다") {
                result shouldBe false
            }
        }

        When("x좌표 10, y좌표 10을 가진 위치와 비교하면") {
            val result = position.match(Position(10, 10))
            Then("true를 반환한다") {
                result shouldBe true
            }
        }
    }
})
