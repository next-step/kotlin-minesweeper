package minesweeper.fixture

import minesweeper.model.board.Board
import minesweeper.model.cell.Cell
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

fun Board.cellAtOrNull(coordinate: Coordinate): Cell? = this.cells.cellAtOrNull(coordinate)
fun Board(area: Area, mineLocator: MineLocator) = Board(area, CellBuilder(area, mineLocator))
