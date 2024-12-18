package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.Location

class GameBoardTest : BehaviorSpec({
    given("GameBoard 를 생성할 때") {
        val height = 10
        val width = 10

        `when`("높이, 너비를 받아 cells를 만들면") {
            val sut = GameBoard.of(height = height, width = width)

            then("cells는 height x width 크기이며 모든 Cell의 좌표가 저장되어 있다") {
                val area = sut.area
                area shouldBe Area(height = height, width = width)

                val result = sut.cells

                result shouldHaveSize height * width
                result.forEach { cell ->
                    cell.location.row in (1..height)
                    cell.location.column in (1..width)
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

        `when`("cells를 직접 받아서 GameBoard2를 만들 때 모든 행의 너비가 똑같지 않은 cells를 받으면") {
            val cells =
                listOf(
                    ClosedCell(oneByOneLocation),
                    ClosedCell(oneByTwoLocation),
                    ClosedCell(oneByThreeLocation),
                    ClosedCell(twoByOneLocation),
                    ClosedCell(twoByTwoLocation),
                    ClosedCell(threeByOneLocation),
                    ClosedCell(threeByTwoLocation),
                    ClosedCell(threeByThreeLocation),
                )

            then("IllegalArgumentException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    GameBoard.from(cells)
                }
            }
        }
    }

    given("GameBoard 는 ") {
        val cells = threeByThreeCells
        val sut = GameBoard.from(cells)

        `when`("Location 으로") {
            val location = Location(row = 1, column = 1)
            val result = sut.find(location)

            then("해당 셀을 찾을 수 있다") {
                result shouldBe cells.find { it.location.row == 1 && it.location.column == 1 }
            }
        }

        `when`("GameBoard 내 없는 location 로 cell을 찾으면 ") {
            val notFoundLocations =
                listOf(
                    Location(row = 0, column = 0),
                    Location(row = 3, column = 4),
                    Location(row = 4, column = 1),
                )

            then("null 을 반환한다") {
                notFoundLocations.forEach { location ->
                    sut.find(location) shouldBe null
                }
            }
        }
    }
})
