package minesweeper.fixture

import minesweeper.model.board.Board
import minesweeper.model.board.BoardState
import minesweeper.model.cell.Cell
import minesweeper.model.cell.CellGenerator
import minesweeper.model.cell.MineLocator
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.Coordinate

fun Board.cellAtOrNull(coordinate: Coordinate): Cell? = this.cells.cellAtOrNull(coordinate)
fun Board(area: Area, mineLocator: MineLocator) = Board(
    area = area,
    initialBoardState = BoardState.Ready(area, CellGenerator(area, mineLocator))
)
