package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MinesTest : BehaviorSpec({
    Given("x좌표와 y좌표가 주어지면") {
        val x = 1
        val y = 1
        val mine = Mine(x, y)
        When("Mines는") {
            val mines = Mines(mapOf(Pair(x, y) to mine))
            Then("주어진 좌표에 해당하는 지뢰를 찾는다.") {
                mines.findBy(x, y) shouldBe mine
            }
        }
    }
})
