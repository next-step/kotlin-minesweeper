package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineMapTest : BehaviorSpec({

    given("높이 5, 너비 5인 지뢰판이 주어진다면") {
        val mineSweeperMap = MineSweeperMap(Height(5), Width(5))
        When("지뢰판 좌표를 생성할 시") {
            val result = mineSweeperMap.createPosition()
            Then("좌표의 개수는 25개이다") {
                result.count() shouldBe 25
            }
        }
    }

    given("높이 5, 너미5인 지뢰판이 주어진다면") {
        val mineSweeperMap = MineSweeperMap(Height(5), Width(5))
        When("지뢰판 좌표(1,1)이 주어진다면") {
            val result = mineSweeperMap.isInMap(Position(1, 1))
            Then("true를 반환한다") {
                result shouldBe true
            }
        }

        When("지뢰판 좌표(6,5) 주어진다면") {
            val result = mineSweeperMap.isInMap(Position(6, 5))
            Then("false를 반환한다") {
                result shouldBe false
            }
        }
    }
})
