package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Area
import minesweeper.domain.Board
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
                board.getAllCell().filterIsInstance<Cell.Mine>().all(Cell::isOpened)
                board.getAllCell().filterIsInstance<Cell.Block>().none(Cell::isOpened)
            }
        }
    }
})
