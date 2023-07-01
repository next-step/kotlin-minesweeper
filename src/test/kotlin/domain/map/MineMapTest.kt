package domain.map

import domain.MineSweeperInitProperty
import domain.cell.Cell
import domain.collections.nestedList
import domain.math.toPositive
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineMapTest : BehaviorSpec({

    Given("높이 100, 가로 50 초기화 속성이 준비 되었을 떄") {
        val mineSweeperInitProperty = MineSweeperInitProperty(
            height = 100.toPositive(),
            width = 50.toPositive(),
            mineCount = 1.toPositive()
        )

        Then("초기화 속성으로 맵을 만들면 높이 100, 가로 50 크기의 맵이 만들어진다") {
            val mineMap = MineMap.create(
                mineSweeperInitProperty = mineSweeperInitProperty,
            )
            val expected = nestedList(
                columnSize = mineSweeperInitProperty.height.value,
                rowSize = mineSweeperInitProperty.width.value
            ) { _, _ ->
                Cell
            }
            mineMap.cells shouldBe expected
        }
    }
})
