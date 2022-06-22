package minesweeper

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate

class BoardTest : DescribeSpec({

    describe("remainMineCount") {
        context("보드에 지뢰가 3개 존재하는 경우") {
            it("3을 리턴한다..") {
                val firstLineCells = listOf(Cell.Mine(Coordinate(0, 0)), Cell.Mine(Coordinate(1, 0)))
                val secondLineCells = listOf(Cell.Mine(Coordinate(0, 1)), Cell.None(Coordinate(1, 1)))

                val board = Board(firstLineCells + secondLineCells)

                board.remainMineCount() shouldBe 3
            }
        }
    }

    describe("groupByColumn") {
        context("2줄 짜리 보드가 주어질 경우") {
            it("각 줄에 해당하는 cell 들을 리턴한다.") {
                val firstLineCells = listOf(Cell.None(Coordinate(0, 0)), Cell.None(Coordinate(1, 0)))
                val secondLineCells = listOf(Cell.None(Coordinate(0, 1)), Cell.None(Coordinate(1, 1)))

                val board = Board(firstLineCells + secondLineCells)

                board.groupByColumn()[0] shouldBe firstLineCells
                board.groupByColumn()[1] shouldBe secondLineCells
            }
        }
    }
})
