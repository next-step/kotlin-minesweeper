package domain.mine

import domain.MineSweeperInitProperty
import domain.math.toPositive
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class RealMineCoordinatesCreatorTest : BehaviorSpec({

    val height = 10
    val width = 5
    val mineCount = 49

    Given("높이 ${height}, 가로 ${width}, 지뢰 개수 ${mineCount}개가 초기화 속성으로 주어졌을 때") {
        val mineSweeperInitProperty = MineSweeperInitProperty(
            height = 10.toPositive(),
            width = 5.toPositive(),
            mineCount = 49.toPositive()
        )

        When("지뢰 목록을 만들면") {
            val mineCoordinates = RealMineCoordinatesCreator().create(mineSweeperInitProperty)

            Then("${mineCount}개의 지뢰 좌표가 만들어진다") {
                mineCoordinates.size shouldBe 49
            }

            Then("모든 지뢰의 좌표는 높이 가로 범위 안에 존재한다") {
                mineCoordinates.all { it.y < height && it.x < width }
            }
        }
    }
})
