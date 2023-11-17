package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineSweeperIndexTest : BehaviorSpec({

    given("지뢰판의 인덱스와 지뢰가 주어지면") {
        val position = Position(1, 1)
        val mines = Mines(listOf(Mine(Position(1, 1))))
        val mineSweeperIndex = MineSweeperIndex(position)
        When("지뢰의 개수를 구할 때") {
            val result = mineSweeperIndex.mineCount(mines)
            Then("지뢰의 개수는 0이다") {
                result shouldBe 0
            }
        }
    }
})
