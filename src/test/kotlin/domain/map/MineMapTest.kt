package domain.map

import domain.MineSweeperInitProperty
import domain.cell.Cell
import domain.math.toPositive
import domain.mine.MockMineCoordinatesCreator
import domain.mine.RealMineCoordinatesCreator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineMapTest : BehaviorSpec({

    Given("높이 100, 가로 50 초기화 속성이 준비 되었을 떄") {
        val mineSweeperInitProperty = MineSweeperInitProperty(
            height = 100.toPositive(),
            width = 50.toPositive(),
            mineCount = 1.toPositive()
        )

        When("맵을 만들면") {
            val mineMap = MineMap.create(
                mineSweeperInitProperty = mineSweeperInitProperty,
                mineCoordinatesCreator = RealMineCoordinatesCreator(),
            )

            Then("높이 100 맵이 만들어진다") {
                mineMap.cells.size shouldBe mineSweeperInitProperty.height.value
            }

            Then("가로 50 맵이 만들어진다") {
                mineMap.cells.first().size shouldBe mineSweeperInitProperty.width.value
            }
        }
    }

    Given("지뢰 개수 5 초기화 속성이 준비 되었을 떄") {
        val mineSweeperInitProperty = MineSweeperInitProperty(
            height = 100.toPositive(),
            width = 50.toPositive(),
            mineCount = 5.toPositive()
        )
        When("맵을 만들면") {
            val mockMineCoordinates = setOf(
                Coordinate(0, 0),
                Coordinate(10, 10),
                Coordinate(15, 5),
                Coordinate(30, 30),
                Coordinate(40, 45),
            )
            val mineMap = MineMap.create(
                mineSweeperInitProperty = mineSweeperInitProperty,
                mineCoordinatesCreator = MockMineCoordinatesCreator { mockMineCoordinates },
            )

            Then("초기화 속성에 기재된 지뢰 개수만큼 지뢰가 맵에 생성된다") {
                val mines = mineMap.cells.flatten().filterIsInstance<Cell.Mine>()
                mines.size shouldBe mockMineCoordinates.size
            }

            Then("만들어진 지뢰 좌표에 지뢰가 존재한다.") {
                mockMineCoordinates.all { (x, y) -> mineMap.cells[y][x] is Cell.Mine } shouldBe true
            }
        }
    }
})
