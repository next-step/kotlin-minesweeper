package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineTest : BehaviorSpec({

    given("x좌표가 1이고 y좌표가 1인 지뢰가 있을 때") {
        val x = 1
        val y = 1
        When("지뢰를 생성할 때") {
            val mine = Mine(x, y)
            Then("x좌표는 1이다") {
                mine.x shouldBe x
            }
            Then("y좌표는 1이다") {
                mine.y shouldBe y
            }
        }
    }
})
