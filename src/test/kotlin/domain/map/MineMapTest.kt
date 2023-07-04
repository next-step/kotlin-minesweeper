package domain.map

import domain.MineSweeperInitProperty
import domain.cell.Cell
import domain.math.toPositive
import domain.mine.MockMineCoordinatesCreator
import domain.mine.RealMineCoordinatesCreator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import utils.nestedList

class MineMapTest : BehaviorSpec({

    Given("높이 100, 가로 50 초기화 속성이 준비 되었을 떄") {
        val mineSweeperInitProperty = MineSweeperInitProperty(
            height = 100.toPositive(),
            width = 50.toPositive(),
            mineCount = 1.toPositive()
        )

        When("맵을 만들면") {
            val mineMapFactory = RealMineMapFactory(RealMineCoordinatesCreator())
            val mineMap = mineMapFactory.create(
                mineSweeperInitProperty = mineSweeperInitProperty,
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
            val mineMapFactory = RealMineMapFactory(MockMineCoordinatesCreator { mockMineCoordinates })
            val mineMap = mineMapFactory.create(
                mineSweeperInitProperty = mineSweeperInitProperty,
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

    Given("6 * 5맵에 8개의 지뢰가 존재할 때") {
        val mineSweeperInitProperty = MineSweeperInitProperty(
            height = 6.toPositive(),
            width = 5.toPositive(),
            mineCount = 8.toPositive()
        )
        val mockMineCoordinates = setOf(
            Coordinate(1, 1),
            Coordinate(2, 1),
            Coordinate(3, 1),
            Coordinate(1, 2),
            Coordinate(3, 2),
            Coordinate(1, 3),
            Coordinate(2, 3),
            Coordinate(3, 3),
        )
        val mineMapFactory = RealMineMapFactory(MockMineCoordinatesCreator { mockMineCoordinates })
        val cells = mineMapFactory.create(
            mineSweeperInitProperty = mineSweeperInitProperty,
        ).cells

        /*
         1 2 3 2 1
         2 * * * 2
         3 * 0 * 3
         2 * * * 2
         1 2 3 2 1
         0 0 0 0 0
         */

        forAll(
            row(0, 0, 1),
            row(1, 0, 2),
            row(2, 0, 3),
            row(3, 0, 2),
            row(4, 0, 1),
            row(0, 1, 2),
            row(4, 1, 2),
            row(0, 2, 3),
            row(4, 2, 3),
            row(0, 3, 2),
            row(4, 3, 2),
            row(0, 4, 1),
            row(1, 4, 2),
            row(2, 4, 3),
            row(3, 4, 2),
            row(4, 4, 1),
            row(0, 5, 0),
            row(1, 5, 0),
            row(2, 5, 0),
            row(3, 5, 0),
            row(4, 5, 0),
        ) { x, y, expectedMineCount ->
            Then("($x, $y)의 지뢰 개수는 ${expectedMineCount}이다") {
                (cells[y][x] as Cell.Ground).aroundMineCount.value shouldBe expectedMineCount
            }
        }
    }
})
