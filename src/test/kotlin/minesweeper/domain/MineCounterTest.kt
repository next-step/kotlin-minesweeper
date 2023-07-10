package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.comparables.shouldNotBeGreaterThan
import io.kotest.matchers.shouldBe
import minesweeper.view.BoardStatus

class MineCounterTest : StringSpec({

    "지뢰가 없는 보드판은 모든 칸의 지뢰 개수가 0이다." {
        val board = BoardGenerator.createBoard(BoardStatus(5, 5, 0))
        MineCounter.calculateNeighborMines(board)
        board.forEachCell { point ->
            val cell = board.getCell(point)
            if (cell is EmptyCell) {
                cell.mineCount shouldBe 0
            }
        }
    }

    "지뢰가 하나만 있는 보드판에서 모든 칸의 지뢰 개수는 1을 넘지 않는다." {
        val board = BoardGenerator.createBoard(BoardStatus(5, 5, 1))
        MineCounter.calculateNeighborMines(board)
        board.forEachCell { point ->
            val cell = board.getCell(point)
            if (cell is EmptyCell) {
                cell.mineCount shouldNotBeGreaterThan 1
            }
        }
    }

    "지뢰의 개수만큼이 최대 인접 지뢰 개수가 되어야 한다" {
        forAll(
            row(BoardStatus(5, 5, 3)),
            row(BoardStatus(5, 5, 4)),
            row(BoardStatus(5, 5, 5)),
            row(BoardStatus(5, 5, 6)),
            row(BoardStatus(5, 5, 7)),
            row(BoardStatus(5, 5, 8))
        ) { boardStatus ->
            val board = BoardGenerator.createBoard(boardStatus)
            MineCounter.calculateNeighborMines(board)
            board.forEachCell { point ->
                val cell = board.getCell(point)
                if (cell is EmptyCell) {
                    cell.mineCount shouldNotBeGreaterThan boardStatus.mineCount
                }
            }
        }
    }
})
