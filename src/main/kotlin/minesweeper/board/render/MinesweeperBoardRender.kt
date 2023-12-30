package minesweeper.board.render

import minesweeper.board.BoardElement
import minesweeper.board.GameBoard
import minesweeper.board.MinesweeperGameBoard
import minesweeper.cell.Cell
import minesweeper.cell.CellType
import minesweeper.position.Position

class MinesweeperBoardRender(
    private val mines: Set<Position>
): BoardRenderStrategy {

    override fun invoke(boardElement: BoardElement): GameBoard {
        val board = List(boardElement.width) { col ->
            List(boardElement.height) { row ->
                makeCell(col, row, INIT_CELL_NUMBER)
            }
        }

        mineMarking(board, boardElement)
        return MinesweeperGameBoard(board)
    }

    private fun makeCell(col: Int, row: Int, value: Char): Cell {
        val position = Position(col, row)
        val type = if (mines.contains(position)) CellType.MINE else CellType.NORMAL
        return Cell(position, type, value)
    }

    private fun mineMarking(board: List<List<Cell>>, boardElement: BoardElement) {
        mines.forEach { mine ->
            mine.nearPositions(boardElement)
                .forEach { if (!board[it.col][it.row].isMine()) board[it.col][it.row].increaseValue() }
        }
    }

    companion object {
        private const val INIT_CELL_NUMBER = '0'
    }
}
