package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineMapTest : BehaviorSpec({

    given("높이와 너비값이 10씩 주어지면") {
        val height = Height(10)
        val width = Width(10)
        When("지뢰지도를 생성할 때") {
            val mineMap = MineMap(height, width)
            Then("지뢰지도의 높이는 10이다") {
                mineMap.height shouldBe height
            }
            Then("지뢰지도의 너비는 10이다") {
                mineMap.width shouldBe width
            }
        }
    }
})
