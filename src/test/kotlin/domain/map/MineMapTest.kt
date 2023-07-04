package domain.map

import domain.MineSweeperInitProperty
import domain.math.toPositive
import domain.mine.RealMineCoordinatesCreator
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

    Given("5 * 5 맵이 준비되어 있을 때") {
        val initProperty = MineSweeperInitProperty(
            height = 5.toPositive(),
            width = 5.toPositive(),
            mineCount = 1.toPositive()
        )
        val mineMap = RealMineMapFactory(RealMineCoordinatesCreator()).create(initProperty)

        Then("0, 0은 맵 안쪽 좌표이다") {
            mineMap.isIn(Coordinate(0, 0)) shouldBe true
        }

        Then("4, 0은 맵 안쪽 좌표이다") {
            mineMap.isIn(Coordinate(4, 0)) shouldBe true
        }

        Then("5, 0은 맵 안쪽 좌표가 아니다") {
            mineMap.isIn(Coordinate(5, 0)) shouldBe false
        }

        Then("0, 4는 맵 안쪽 좌표이다") {
            mineMap.isIn(Coordinate(0, 4)) shouldBe true
        }

        Then("0, 5는 맵 안쪽 좌표가 아니다") {
            mineMap.isIn(Coordinate(0, 5)) shouldBe false
        }

        Then("4, 4는 맵 안쪽 좌표이다") {
            mineMap.isIn(Coordinate(4, 4)) shouldBe true
        }

        Then("5, 5는 맵 안쪽 좌표가 아니다") {
            mineMap.isIn(Coordinate(5, 5)) shouldBe false
        }
    }
})
