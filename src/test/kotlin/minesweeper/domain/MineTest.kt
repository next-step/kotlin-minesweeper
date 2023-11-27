package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineTest : BehaviorSpec({
    Given("x좌표와 y좌표가 주어지면") {
        val x = 1
        val y = 1
        When("지뢰는") {
            val mine = Mine(x, y)
            Then("주어진 x좌표와 y좌표를 갖는 지뢰가 생성된다.") {
                mine.x shouldBe x
                mine.y shouldBe y
            }
        }
    }
})
