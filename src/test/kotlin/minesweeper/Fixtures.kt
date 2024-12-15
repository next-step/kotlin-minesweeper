package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate
import minesweeper.domain.EmptyCell
import minesweeper.domain.MinedCell

fun minedCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to MinedCell()

fun emptyCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to EmptyCell()

fun boardOf(vararg cells: Pair<Coordinate, Cell>): Board = Board(cells.toMap())
