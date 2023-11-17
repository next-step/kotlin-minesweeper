package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineSweeperTest : BehaviorSpec({

    given("지뢰판(10x10)과 지뢰2개가 주어지면") {
        val mineSweeperMap = MineSweeperMap(Height(10), Width(10))
        val mines = Mines(listOf(Mine(1 to 1), Mine(1 to 2)))
        When("지뢰찾기 객체를 생성하면") {
            val mineSweeper = MineSweeper(mineSweeperMap, mines)
            Then("지뢰판은 10x10이다") {
                mineSweeper.mineSweeperMap shouldBe mineSweeperMap
            }
            Then("지뢰는 2개이다") {
                mineSweeper.mines shouldBe mines
            }
        }
    }

    given("지뢰판(2x2)와 지뢰(2,3)이 주어지면") {
        val mineSweeperMap = MineSweeperMap(Height(2), Width(2))
        val mines = Mines(listOf(Mine(2 to 3)))
        When("지뢰찾기 객체를 생성하면") {
            val exception = shouldThrow<IllegalArgumentException> {
                MineSweeper(mineSweeperMap, mines)
            }
            Then("IllegalArgumentException이 발생한다") {
                exception.message shouldBe "지뢰의 위치가 지뢰지도의 범위를 벗어났습니다."
            }
        }
    }
})
