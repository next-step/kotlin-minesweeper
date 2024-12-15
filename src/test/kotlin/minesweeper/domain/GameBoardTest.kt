package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class GameBoardTest : BehaviorSpec({
    given("GameBoard 를 생성할 때") {
        val rowLength = 10
        val columnLength = 10

        `when`("행 길이, 열 길이를 받아 grid를 만들면") {
            val sut = GameBoard.of(rowLength, columnLength)

            then("grid는 rowLength x columnLength 크기이며 모든 Cell의 좌표가 저장되어 있다") {
                val area = sut.area
                area shouldBe Area(height = rowLength, width = columnLength)

                val result: List<Row> = sut.rows()

                result shouldHaveSize rowLength
                result.forEachIndexed { rowIndex, row ->
                    row.cells() shouldHaveSize columnLength
                    row.cells().forEachIndexed { columnIndex, cell ->
                        cell.location() shouldBe Location(rowIndex + 1, columnIndex + 1)
                    }
                }
            }
        }

        `when`("행 길이가 1 미만이면") {
            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    GameBoard.of(
                        height = 0,
                        width = columnLength,
                    )
                }
            }
        }

        `when`("열 길이가 1 미만이면") {
            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    GameBoard.of(
                        height = rowLength,
                        width = 0,
                    )
                }
            }
        }

        `when`("grid를 직접 받아서 GameBoard를 만들 때 모든 행의 열 길이가 똑같지 않은 Grid를 받으면") {
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
})
