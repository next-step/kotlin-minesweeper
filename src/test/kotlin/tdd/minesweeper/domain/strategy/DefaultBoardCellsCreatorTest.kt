package tdd.minesweeper.domain.strategy

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.AdjacentMines
import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.ClosedCell
import tdd.minesweeper.domain.Location

class DefaultBoardCellsCreatorTest : BehaviorSpec({

    given("5 x 5 영역, 지뢰 개수 5개를 받고") {
        val area = Area(height = 5, width = 5)
        val countOfMines = 5
        val sut: BoardCellsCreator = DefaultBoardCellsCreator()

        `when`("빈 수동 지뢰 위치 목록을 받아 25개의 셀 목록을 만들면") {
            val inputManualMineLocations: Set<Location> = emptySet()
            val result = sut.createCells(area, countOfMines, inputManualMineLocations)

            then("25개의 닫힌 셀이다") {
                result.size shouldBe 25
                result.all { it is ClosedCell }.shouldBeTrue()
            }

            then("25개의 셀 중 5개는 지뢰를 가지고 있다") {
                result.filter { it.hasMine() }.size shouldBe 5
            }
        }

        `when`("5개의 서로 다른 지뢰 위치 목록을 받아 25개의 셀 목록을 만들면") {
            val inputManualMineLocations: Set<Location> =
                setOf(
                    Location(1, 4),
                    Location(1, 5),
                    Location(2, 1),
                    Location(4, 3),
                    Location(5, 1),
                )
            val result = sut.createCells(area, countOfMines, inputManualMineLocations)

            then("해당 위치에 지뢰를 가진 셀 목록을 반환한다") {
                result.filter { it.hasMine() }.size shouldBe 5

                inputManualMineLocations.forEach { location ->
                    val cell = result[location.toIndex(area.width)]
                    cell.hasMine() shouldBe true
                }
            }

            then("지뢰가 아닌 셀들에 인접 지뢰 개수를 표시할 수 있다") {
                // -1 은 지뢰
                val expectedAdjacentMines =
                    listOf(
                        listOf(1, 1, 1, -1, -1),
                        listOf(-1, 1, 1, 2, 2),
                        listOf(1, 2, 1, 1, 0),
                        listOf(1, 2, -1, 1, 0),
                        listOf(-1, 2, 1, 1, 0),
                    )

                for (row in 1..area.height) {
                    for (col in 1..area.width) {
                        val cellIndex = Location(row, col).toIndex(area.width)
                        val cell = result[cellIndex]
                        val expectedMineCount = expectedAdjacentMines[row - 1][col - 1]

                        if (expectedMineCount == -1) {
                            cell.hasMine() shouldBe true
                        } else {
                            cell.hasMine() shouldBe false
                            cell.adjacentMines shouldBe AdjacentMines(expectedMineCount)
                        }
                    }
                }
            }
        }

        `when`("4개의 서로 다른 지뢰 위치 목록을 받아 25개의 셀 목록을 만들면") {
            val inputManualMineLocations: Set<Location> =
                setOf(
                    Location(1, 4),
                    Location(1, 5),
                    Location(2, 1),
                    Location(4, 3),
                )
            val result = sut.createCells(area, countOfMines, inputManualMineLocations)

            then("4개의 지정 위치와 1개의 랜덤 위치에 지뢰를 가진 셀 목록을 반환한다") {
                result.filter { it.hasMine() }.size shouldBe 5

                inputManualMineLocations.forEach { location ->
                    val cell = result[location.toIndex(area.width)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("6개의 서로 다른 위치 목록을 받아 25개의 셀 목록을 만들면") {
            val inputManualMineLocations: Set<Location> =
                setOf(
                    Location(1, 4),
                    Location(1, 5),
                    Location(2, 1),
                    Location(4, 3),
                    Location(5, 1),
                    Location(5, 2),
                )
            val result = sut.createCells(area, countOfMines, inputManualMineLocations)

            then("앞에서 5개의 위치에 지뢰를 가진 셀 목록을 반환한다") {
                result.filter { it.hasMine() }.size shouldBe 5

                inputManualMineLocations.take(5).forEach { location ->
                    val cell = result[location.toIndex(area.width)]
                    cell.hasMine() shouldBe true
                }
            }
        }

        `when`("1개의 유효하지 않은 지뢰 위치 + 5개의 서로 다른 지뢰 위치 목록을 받아 25개의 셀 목록을 만들면") {
            val invalidMineLocation = Location(0, 4)
            val inputManualMineLocations: Set<Location> =
                setOf(
                    Location(1, 4),
                    Location(1, 5),
                    invalidMineLocation,
                    Location(2, 1),
                    Location(4, 3),
                    Location(5, 1),
                )
            val result = sut.createCells(area, countOfMines, inputManualMineLocations)

            then("유효한 5개의 지뢰 위치에 지뢰를 가진 셀 목록을 반환한다") {
                result.filter { it.hasMine() }.size shouldBe 5

                inputManualMineLocations.filterNot { it == invalidMineLocation }.forEach { location ->
                    val cell = result[location.toIndex(area.width)]
                    cell.hasMine() shouldBe true
                }
            }
        }
    }
})
