package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineSweeperTest : BehaviorSpec({

    given("지뢰판(10x10)과 지뢰2개가 주어지면") {
        val mineMap = MineMap(Height(10), Width(10))
        val mines = Mines(listOf(Mine(1, 1), Mine(1, 2)))
        When("지뢰찾기 객체를 생성하면") {
            val mineSweeper = MineSweeper(mineMap, mines)
            Then("지뢰판은 10x10이다") {
                mineSweeper.mineMap shouldBe mineMap
            }
            Then("지뢰는 2개이다") {
                mineSweeper.mineList shouldBe mines.mines
            }
        }
    }

    given("지뢰판(2x2)와 지뢰(2,3)이 주어지면") {
        val mineMap = MineMap(Height(2), Width(2))
        val mines = Mines(listOf(Mine(2, 3)))
        When("지뢰찾기 객체를 생성하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                MineSweeper(mineMap, mines)
            }
            Then("IllegalArgumentException이 발생한다") {
                exception.message shouldBe "지뢰의 위치가 지뢰지도의 범위를 벗어났습니다."
            }
        }
    }
})
