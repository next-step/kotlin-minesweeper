package minesweeper.board.render

import minesweeper.board.Board
import minesweeper.board.BoardElement
import minesweeper.board.GameBoard
import minesweeper.board.MinesweeperBoard
import minesweeper.cell.Cell
import minesweeper.cell.CellType
import minesweeper.position.Position

class MinesweeperBoardRender(
    private val mines: Set<Position>
): BoardRenderStrategy {

    override fun invoke(boardElement: BoardElement, value: Char): GameBoard {
        val board = List(boardElement.height) { row ->
            List(boardElement.width) { col ->
                makeCell(col, row, value)
            }
        }
        val gameBoard = Board(board)
        mineMarking(gameBoard, boardElement)
        return MinesweeperBoard(gameBoard)
    }

    private fun makeCell(col: Int, row: Int, value: Char): Cell {
        val position = Position(col, row)
        val type = if (mines.contains(position)) CellType.MINE else CellType.NORMAL
        return Cell(position, type, value)
    }

    private fun mineMarking(board: Board, boardElement: BoardElement) {
        mines.forEach { mine ->
            mine.nearPositions(boardElement)
                .forEach { if (!board[it.row][it.col].isMine()) board[it.row][it.col].increaseValue() }
        }
    }
}
