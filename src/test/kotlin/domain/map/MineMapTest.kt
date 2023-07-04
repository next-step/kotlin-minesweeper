package domain.map

import domain.MineSweeperInitProperty
import domain.math.toPositive
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineMapTest : BehaviorSpec({

    Given("10 * 10 맵이 준비되어 있을 때") {
        val initProperty = MineSweeperInitProperty(
            height = 10.toPositive(),
            width = 10.toPositive(),
            mineCount = 1.toPositive()
        )
        val mineMap = RealMineMapFactory { setOf(Coordinate(5, 5)) }.create(initProperty)

        When("0, 0을 열면") {
            mineMap.open(Coordinate(0, 0))

            Then("0, 0 셀이 열린다") {
                mineMap.capture().cells[0][0].openState.isOpen() shouldBe true
            }
        }
    }
})
