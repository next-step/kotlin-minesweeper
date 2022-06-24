package minesweeper.fixture

import minesweeper.model.board.Board
import minesweeper.model.cell.Cell
import minesweeper.model.coordinate.Coordinate

fun Board.cellAtOrNull(coordinate: Coordinate): Cell? = this.cells.cellAtOrNull(coordinate)
