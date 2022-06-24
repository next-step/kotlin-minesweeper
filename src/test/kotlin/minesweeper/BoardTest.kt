package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Area
import minesweeper.domain.Board
import minesweeper.domain.BoardOpenResult
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate
import minesweeper.domain.MineCount

class BoardTest : DescribeSpec({

    describe("init") {
        context("크기보다 지뢰의 개수가 많으면") {
            it("Invalid 상태를 반환한다.") {
                shouldThrow<IllegalArgumentException> {
                    Board(Area(1, 1), MineCount(10))
                }
            }
        }
    }

    describe("remainMineCount") {
        context("보드에 지뢰가 3개 존재하는 경우") {
            it("3을 리턴한다..") {
                val board = Board(
                    "**",
                    "*3"
                )

                board.remainMineCount() shouldBe 3
            }
        }
    }

    describe("groupByColumn") {
        context("2줄 짜리 보드가 주어질 경우") {
            it("각 줄에 해당하는 cell 들을 리턴한다.") {
                val board = Board(
                    "00",
                    "00"
                )

                val firstLineCells = listOf(Cell.Block(Coordinate(0, 0)), Cell.Block(Coordinate(1, 0)))
                val secondLineCells = listOf(Cell.Block(Coordinate(0, 1)), Cell.Block(Coordinate(1, 1)))

                board.groupByColumn()[0] shouldBe firstLineCells
                board.groupByColumn()[1] shouldBe secondLineCells
            }
        }
    }

    describe("openCell") {
        context("보드에서 하나의 좌표를 열면") {
            it("연결되어 있으면서 지뢰가 없는 좌표가 전부 열린다.") {
                val board = Board(
                    "01*10",
                    "11110",
                    "1*100"
                )

                board.open(Coordinate(0, 0))

                board.cells.count { it.isOpened() } shouldBe 4
                board.groupByColumn()[0]!![0].isOpened() shouldBe true
                board.groupByColumn()[0]!![1].isOpened() shouldBe true
                board.groupByColumn()[1]!![0].isOpened() shouldBe true
                board.groupByColumn()[1]!![1].isOpened() shouldBe true
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

    describe("isCompleted") {
        context("보드에 존재하는 지뢰가 아닌 모든 Cell 이 열리면") {
            it("true 를 리턴한다.") {
                val board = Board(listOf(OpenedCell(Coordinate(0, 0))))

                board.isCompleted() shouldBe true
            }
        }
    }

    describe("openAllMine") {
        context("보드에 존재하는 모든 지뢰를") {
            it("open 상태로 바꾼다.") {
                val board = Board(
                    "01*10",
                    "11110",
                    "1*100"
                )

                board.openAllMine()
                board.cells.filterIsInstance<Cell.Mine>().all(Cell::isOpened)
                board.cells.filterIsInstance<Cell.Block>().none(Cell::isOpened)
            }
        }
    }
})
