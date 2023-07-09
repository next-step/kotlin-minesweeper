package minesweeper.domain.test

import minesweeper.domain.board.Board
import minesweeper.domain.board.Cell
import minesweeper.domain.board.CellBoard
import minesweeper.domain.board.Mine
import minesweeper.domain.vo.Position

object TestObjectGenerator {
    fun cellBoard(minePositions: List<Position>, width: Int, height: Int): CellBoard {
        val board = (0 until height).map { y ->
            (0 until width).map { x ->
                val position = Position.of(x = x, y = y)
                if (position in minePositions) {
                    Cell(Mine.ACTIVE, position)
                } else {
                    Cell(Mine.INACTIVE, position)
                }
            }
        }
        return CellBoard(Board(board))
    }
}
