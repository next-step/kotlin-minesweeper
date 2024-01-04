package minesweeper.board.render

import minesweeper.board.BoardElement
import minesweeper.board.DefaultGameBoard
import minesweeper.board.GameBoard
import minesweeper.cell.Cell
import minesweeper.cell.CellType
import minesweeper.position.Position

class DefaultBoardRender(
    private val mines: Set<Position>
): BoardRenderStrategy {

    override fun invoke(boardElement: BoardElement): GameBoard {
        val board = List(boardElement.width) { col ->
            List(boardElement.height) { row ->
                Cell(Position(col, row), CellType.NORMAL, INIT_CELL)
            }
        }

        return DefaultGameBoard(board, boardElement.height * boardElement.width - mines.size)
    }

    companion object {
        private const val INIT_CELL = 'C'
    }
}
