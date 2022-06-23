package minesweeper

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Board
import minesweeper.domain.BoardOpenResult
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate

class BoardTest : DescribeSpec({

    describe("remainMineCount") {
        context("보드에 지뢰가 3개 존재하는 경우") {
            it("3을 리턴한다..") {
                val firstLineCells = listOf(Cell.Mine(Coordinate(0, 0)), Cell.Mine(Coordinate(1, 0)))
                val secondLineCells = listOf(Cell.Mine(Coordinate(0, 1)), Cell.Block(Coordinate(1, 1)))

                val board = Board(firstLineCells + secondLineCells)

                board.remainMineCount() shouldBe 3
            }
        }
    }

    describe("groupByColumn") {
        context("2줄 짜리 보드가 주어질 경우") {
            it("각 줄에 해당하는 cell 들을 리턴한다.") {
                val firstLineCells = listOf(Cell.Block(Coordinate(0, 0)), Cell.Block(Coordinate(1, 0)))
                val secondLineCells = listOf(Cell.Block(Coordinate(0, 1)), Cell.Block(Coordinate(1, 1)))

                val board = Board(firstLineCells + secondLineCells)

                board.groupByColumn()[0] shouldBe firstLineCells
                board.groupByColumn()[1] shouldBe secondLineCells
            }
        }
    }

    describe("openCell") {
        context("2 * 2 보드에서 1,1 좌표에 폭탄이 있는 경우 0,0 좌표를 열면") {
            it("(0,0), (0,1), (1,0) 좌표가 열린다.") {
                val firstLineCells = listOf(Cell.Block(Coordinate(0, 0), 1), Cell.Block(Coordinate(1, 0), 1))
                val secondLineCells = listOf(Cell.Block(Coordinate(0, 1), 1), Cell.Mine(Coordinate(1, 1)))

                val board = Board(firstLineCells + secondLineCells)

                board.open(Coordinate(0, 0))

                val expectedFirstLineCells = listOf(OpenedCell(Coordinate(0, 0), 1), OpenedCell(Coordinate(1, 0), 1))
                val expectedSecondLineCells = listOf(OpenedCell(Coordinate(0, 1), 1), Cell.Mine(Coordinate(1, 1)))

                board.groupByColumn()[0] shouldBe expectedFirstLineCells
                board.groupByColumn()[1] shouldBe expectedSecondLineCells
            }
        }

        context("좌표를 열었을 때 지뢰가 아니라면") {
            it("성공한 것을 알린다. ( BoardOpenResult.Success 을 리턴한다. )") {
                val board = Board(listOf(Cell.Block(Coordinate(0, 0))))

                board.open(Coordinate(0, 0)) shouldBe BoardOpenResult.Success
            }
        }

        context("좌표를 열었을 때 지뢰가 아니고 이미 열려있다면") {
            it("BoardOpenResult.AlreadyOpened 을 리턴한다.") {
                val board = Board(listOf(OpenedCell(Coordinate(0, 0))))

                board.open(Coordinate(0, 0)) shouldBe BoardOpenResult.AlreadyOpened
            }
        }

        context("좌표를 열었을 때 지뢰라면") {
            it("실패한 것을 알린다. ( BoardOpenResult.Fail 을 리턴한다. )") {
                val board = Board(listOf(Cell.Mine(Coordinate(0, 0))))

                board.open(Coordinate(0, 0)) shouldBe BoardOpenResult.Fail
            }
        }

        context("좌표가 보드판을 벗어났다면") {
            it("BoardOpenResult.NotFound 를 리턴한다.") {
                val board = Board(listOf(Cell.Block(Coordinate(0, 0))))

                board.open(Coordinate(0, 1)) shouldBe BoardOpenResult.NotFound
            }
        }
    }
})

private fun OpenedCell(coordinate: Coordinate, aroundMineCount: Int = 0) =
    Cell.Block(coordinate, aroundMineCount).apply { open() }
