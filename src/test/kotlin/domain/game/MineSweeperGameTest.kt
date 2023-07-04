package domain.game

import domain.MineSweeperInitProperty
import domain.map.Coordinate
import domain.map.RealMineMapFactory
import domain.math.toPositive
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineSweeperGameTest : BehaviorSpec({

    Given("1, 1에 지뢰가 존재할 때") {
        val initProperty = MineSweeperInitProperty(
            height = 10.toPositive(),
            width = 10.toPositive(),
            mineCount = 1.toPositive()
        )
        val mineMap = RealMineMapFactory { setOf(Coordinate(1, 1)) }.create(initProperty)
        val game = MineSweeperGame(mineMap)

        When("1, 1을 열면") {
            val openResult = game.open(Coordinate(1, 1))

            Then("지뢰를 열었다는 결과를 반환한다") {
                (openResult is OpenResult.MineOpened) shouldBe true
            }
        }
    }
})
