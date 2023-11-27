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

    Given("지뢰가 있는지 여부를 알고 싶은 좌표가 주어질때") {
        val x = 1
        val y = 1
        val mine = Mine(x, y)
        When("Mines는") {
            val mines = Mines(mapOf(Pair(x, y) to mine))
            Then("주어진 좌표에 해당하는 지뢰가 있는지 여부를 반환한다.") {
                mines.contains(x, y) shouldBe true
                mines.contains(x + 1, y) shouldBe false
            }
        }
    }
})
