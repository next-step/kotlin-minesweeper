package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineSweeperIndexTest : BehaviorSpec({

    given("지뢰판의 인덱스(1,1)와 지뢰(1,2)가 주어지면") {
        val position = Position(1, 1)
        val mines = Mines(listOf(Mine(Position(1, 2))))
        val mineSweeperIndex = MineSweeperIndex(position)
        When("지뢰의 개수를 구할 때") {
            val result = mineSweeperIndex.mineCount(mines)
            Then("지뢰의 개수는 1이다") {
                result shouldBe 1
            }
        }
    }

    given("지뢰판의 인덱스(1,1)이 주어졌을때") {
        val position = Position(1, 1)
        val mines = Mines(listOf(Mine(Position(1, 1))))
        val mineSweeperIndex = MineSweeperIndex(position)
        When("지뢰가 (1,1) 이라면") {
            val result = mineSweeperIndex.mineCount(mines)
            Then("지뢰이다.") {
                result shouldBe MineSweeperIndex.MINE
            }
        }
    }

    given("지뢰판의 인덱스(1,1)이 주어졌을 때") {
        val position = Position(1, 1)
        val mineSweeperIndex = MineSweeperIndex(position)
        When("지뢰가 없다면") {
            val result = mineSweeperIndex.mineCount(Mines(emptyList()))
            Then("지뢰의 개수는 0이다.") {
                result shouldBe 0
            }
        }
    }
})
