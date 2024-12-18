package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.BasicCell
import minesweeper.domain.cell.Location

class GameBoardTest : BehaviorSpec({
    given("GameBoard 를 생성할 때") {
        val height = 10
        val width = 10

        `when`("높이, 너비를 받아 grid를 만들면") {
            val sut = GameBoard.of(height = height, width = width)

            then("grid는 rowLength x columnLength 크기이며 모든 Cell의 좌표가 저장되어 있다") {
                val area = sut.area
                area shouldBe Area(height = height, width = width)

                val result: List<Row> = sut.rows

                result shouldHaveSize height
                result.forEachIndexed { rowIndex, row ->
                    row.cells() shouldHaveSize width
                    row.cells().forEachIndexed { columnIndex, cell ->
                        cell.location() shouldBe Location(rowIndex + 1, columnIndex + 1)
                    }
                }
            }
        }

        `when`("높이가 1 미만이면") {
            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    GameBoard.of(
                        height = 0,
                        width = width,
                    )
                }
            }
        }

        `when`("너비가 1 미만이면") {
            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    GameBoard.of(
                        height = height,
                        width = 0,
                    )
                }
            }
        }

        `when`("grid를 직접 받아서 GameBoard를 만들 때 모든 행의 너비가 똑같지 않은 Grid를 받으면") {
            val grid =
                listOf(
                    List(3) { BasicCell(row = 1, column = (it + 1)) },
                    List(2) { BasicCell(row = 2, column = (it + 1)) },
                    List(3) { BasicCell(row = 3, column = (it + 1)) },
                )

            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    GameBoard.from(grid)
                }
            }
        }
    }

    given("GameBoard 는 ") {
        val grid = threeByThreeGrid
        val sut = GameBoard.from(grid)

        `when`("Location 으로") {
            val location = Location(row = 1, column = 1)
            val result = sut.find(location)

            then("해당 셀을 찾을 수 있다") {
                result shouldBe grid[0][0]
            }
        }

        `when`("Row 범위 내의 없는 Location 로 Cell을 찾으려고 하면") {
            val location = Location(row = 3, column = 4)
            val result = sut.find(location)

            then("null 을 반환한다") {
                result shouldBe null
            }
        }

        `when`("Row 범위를 벗어난 Location 으로 Cell을 찾으려고 하면") {
            val location = Location(row = 4, column = 1)

            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> { sut.find(location) }
            }
        }

        `when`("allCells() 로") {
            val result = sut.allCells()

            then("존재하는 모든 셀을 1차원 목록으로 평탄화해서 반환한다") {
                result shouldBe grid.flatten()
            }
        }
    }
})
