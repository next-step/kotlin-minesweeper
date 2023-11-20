package minesweeper.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineSweeperIndexesTest : BehaviorSpec({
    given("지뢰판(2,2)의 인덱스(1,1)와 지뢰(1,2)가 주어지면") {
        val mineSweeperMap = MineSweeperMap(Height(2), Width(2)).createPosition()
        val position = Position(1, 1)
        val mines = Mines(listOf(Mine(Position(1, 2))))
        val mineSweeperIndex = MineSweeperIndex(position)
        When("인덱스를 열 때") {
            val result = mineSweeperMap.open(mines, mineSweeperIndex)
            Then("open되는 인덱스는 1개이다.") {
                result.count() shouldBe 1
            }
        }
    }

    given("지뢰판(2,2)의 인덱스(1,1)와 지뢰(1,1)가 주어지면") {
        val mineSweeperMap = MineSweeperMap(Height(2), Width(2)).createPosition()
        val position = Position(1, 1)
        val mines = Mines(listOf(Mine(Position(1, 1))))
        val mineSweeperIndex = MineSweeperIndex(position)
        When("인덱스를 열 때") {
            val result = mineSweeperMap.open(mines, mineSweeperIndex)
            Then("open되는 인덱스는 없다") {
                result.count() shouldBe 0
            }
        }
    }

    given("지뢰판(3,3)의 인덱스(1,1)와 지뢰 (3,3)가 주어지면") {
        val mineSweeperMap = MineSweeperMap(Height(3), Width(3)).createPosition()
        val position = Position(1, 1)
        val mines = Mines(listOf(Mine(Position(3, 3))))
        val mineSweeperIndex = MineSweeperIndex(position)
        When("인덱스를 열 때") {
            val result = mineSweeperMap.open(mines, mineSweeperIndex)
            Then("open되는 인덱스는 8개이다.") {
                result.count() shouldBe 8
            }
        }
    }
})
