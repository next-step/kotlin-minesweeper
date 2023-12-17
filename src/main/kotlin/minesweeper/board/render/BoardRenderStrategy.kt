package minesweeper.board.render

import minesweeper.board.Board
import minesweeper.board.BoardElement
import minesweeper.board.DefaultGameBoard
import minesweeper.board.GameBoard
import minesweeper.cell.Cell
import minesweeper.cell.CellType
import minesweeper.position.Position

fun interface BoardRenderStrategy {

    operator fun invoke(boardElement: BoardElement, value: Char): GameBoard
}

val defaultBoardRender = BoardRenderStrategy { boardElement, value ->
    val array = List(boardElement.height) { row ->
        List(boardElement.width) { col ->
            Cell(Position(col, row), CellType.NORMAL, value)
        }
    }
    DefaultGameBoard(Board(array))
}
