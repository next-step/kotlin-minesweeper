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

    describe("openCell") {
        context("보드에서 하나의 좌표를 열면") {
            it("연결되어 있으면서 지뢰가 없는 좌표가 전부 열린다.") {
                /* 보드 Cell 배치
                0 1 *
                1 2 1
                1 * 1
                */
                val board = SampleBoard(Area(3, 3), MineCount(2), mines = listOf(Coordinate(0, 2), Coordinate(2, 1)))

                board.open(Coordinate(0, 0))

                board.cells.count { it.isOpened() } shouldBe 4
                board.cells.findCell(Coordinate(0, 0))!!.isOpened() shouldBe true
                board.cells.findCell(Coordinate(0, 1))!!.isOpened() shouldBe true
                board.cells.findCell(Coordinate(1, 0))!!.isOpened() shouldBe true
                board.cells.findCell(Coordinate(1, 1))!!.isOpened() shouldBe true
            }
        }

        context("좌표를 열었을 때 지뢰가 아니라면") {
            it("성공한 것을 알린다. ( BoardOpenResult.Success 을 리턴한다. )") {
                val board = SampleBoard(listOf(Cell.Block(Coordinate(0, 0))))

                board.open(Coordinate(0, 0)) shouldBe BoardOpenResult.Success
            }
        }

        context("좌표를 열었을 때 지뢰가 아니고 이미 열려있다면") {
            it("BoardOpenResult.AlreadyOpened 을 리턴한다.") {
                val board = SampleBoard(listOf(OpenedCell(Coordinate(0, 0))))

                board.open(Coordinate(0, 0)) shouldBe BoardOpenResult.AlreadyOpened
            }
        }

        context("좌표를 열었을 때 지뢰라면") {
            it("실패한 것을 알린다. ( BoardOpenResult.Fail 을 리턴한다. )") {
                val board = SampleBoard(listOf(Cell.Mine(Coordinate(0, 0))))

                board.open(Coordinate(0, 0)) shouldBe BoardOpenResult.Fail
            }
        }

        context("좌표가 보드판을 벗어났다면") {
            it("BoardOpenResult.NotFound 를 리턴한다.") {
                val board = SampleBoard(listOf(Cell.Block(Coordinate(0, 0))))

                board.open(Coordinate(0, 1)) shouldBe BoardOpenResult.NotFound
            }
        }
    }

    describe("isCompleted") {
        context("보드에 존재하는 지뢰가 아닌 모든 Cell 이 열리면") {
            it("true 를 리턴한다.") {
                val board = SampleBoard(listOf(OpenedCell(Coordinate(0, 0))))

                board.isCompleted() shouldBe true
            }
        }
    }

    describe("openAllMine") {
        context("보드에 존재하는 모든 지뢰를") {
            it("open 상태로 바꾼다.") {
                /* 보드 Cell 배치
                 0 1 *
                 1 2 1
                 1 * 1
                 */
                val board = SampleBoard(Area(3, 3), MineCount(2), mines = listOf(Coordinate(0, 2), Coordinate(2, 1)))

                board.openAllMine()
                board.cells.filterIsInstance<Cell.Mine>().all(Cell::isOpened)
                board.cells.filterIsInstance<Cell.Block>().none(Cell::isOpened)
            }
        }
    }
})
