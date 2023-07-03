package domain.map

import domain.MineSweeperInitProperty
import domain.cell.Cell
import utils.nestedList
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

    Given("지뢰 개수 8 초기화 속성이 준비 되었을 떄") {
        val mineSweeperInitProperty = MineSweeperInitProperty(
            height = 100.toPositive(),
            width = 50.toPositive(),
            mineCount = 8.toPositive()
        )
        When("맵을 만들면") {
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

            val mineMap = MineMap.create(
                mineSweeperInitProperty = mineSweeperInitProperty,
                mineCoordinatesCreator = MockMineCoordinatesCreator { mockMineCoordinates },
            )

            val hasAroundMineCellCoordinates = listOf(
                Coordinate(0, 0) to 1, // 주변에 1개 (1, 1)
                Coordinate(1, 0) to 2, // 주변에 2개 (1, 1) (2, 1)
                Coordinate(2, 0) to 3, // 주변에 3개 (1, 1) (2, 1) (3, 1)
                Coordinate(3, 0) to 2, // 주변에 2개 (2, 1) (3, 1)
                Coordinate(4, 0) to 1, // 주변에 1개 (3, 1)
                Coordinate(0, 1) to 2, // 주변에 2개 (1, 1) (1, 2)
                Coordinate(4, 1) to 2, // 주변에 2개 (3, 1) (3, 2)
                Coordinate(0, 2) to 3, // 주변에 3개 (1, 1) (1, 2) (1, 3)
                Coordinate(2, 2) to 8, // 주변에 8개 전부
                Coordinate(4, 2) to 3, // 주변에 3개 (3, 1) (3, 2) (3, 3)
                Coordinate(0, 3) to 2, // 주변에 2개 (1, 2) (1, 3)
                Coordinate(4, 3) to 2, // 주변에 2개 (3, 2) (3, 3)
                Coordinate(0, 4) to 1, // 주변에 1개 (1, 3)
                Coordinate(1, 4) to 2, // 주변에 2개 (1, 3) (2, 3)
                Coordinate(2, 4) to 3, // 주변에 3개 (1, 3) (2, 3) (3, 3)
                Coordinate(3, 4) to 2, // 주변에 2개 (2, 3) (3, 3)
                Coordinate(4, 4) to 1, // 주변에 1개 (3, 3)
            )

            Then("주변에 지뢰가 있는 셀은 주변 지뢰 개수 만큼 값을 갖는다") {
                val isAllExpected = hasAroundMineCellCoordinates.all { (coordinate, expectedAroundMineCount) ->
                    val groundCell = (mineMap.cells[coordinate.y][coordinate.x] as Cell.Ground)
                    val actualAroundMineCount = groundCell.aroundMineCount.value
                    actualAroundMineCount == expectedAroundMineCount
                }
                isAllExpected shouldBe true
            }

            Then("주변에 지뢰가 업는 셀은 주변 지뢰 개수가 0이다") {
                val hasAroundMineCellCoordinateSet = hasAroundMineCellCoordinates.map { it.first }.toSet()
                val allCellCoordinates = nestedList(
                    columnSize = mineMap.cells.size,
                    rowSize = mineMap.cells.first().size
                ) { column, row ->
                    Coordinate(x = row, y = column)
                }.flatten()
                    .filter { mockMineCoordinates.contains(it).not() }
                    .filter { hasAroundMineCellCoordinateSet.contains(it).not() }
                val isAllExpected = allCellCoordinates.all { coordinate ->
                    val groundCell = (mineMap.cells[coordinate.y][coordinate.x] as Cell.Ground)
                    val actualAroundMineCount = groundCell.aroundMineCount.value
                    actualAroundMineCount == 0
                }
                isAllExpected shouldBe true
            }
        }
    }
})
