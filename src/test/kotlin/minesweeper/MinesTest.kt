package minesweeper

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MinesTest : BehaviorSpec({
    Given("x좌표와 y좌표를 갖는 지뢰가 주어질 때") {
        val x = 1
        val y = 1
        val mine = Mine(x, y)
        When("Mines가 지뢰를 추가하면") {
            val mines = Mines()
            mines.add(mine)
            Then("Mines는 주어진 지뢰들의 좌표를 키로, 지뢰를 값으로 추가한다.") {
                mines.findBy(x, y) shouldBe mine
            }
        }
    }
})
