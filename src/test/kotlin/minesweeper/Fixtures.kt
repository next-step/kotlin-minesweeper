package minesweeper

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate
import minesweeper.domain.PlayableBoard
import minesweeper.domain.UndetonatedMineCell
import minesweeper.domain.UnopenedCell

fun minedCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UndetonatedMineCell()

fun emptyCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UnopenedCell()

fun undetonatedMineCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UndetonatedMineCell()

fun detonatedMineCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UndetonatedMineCell()

fun unopenedCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UnopenedCell()

fun openedCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UnopenedCell()

fun boardOf(vararg cells: Pair<Coordinate, Cell>): Board = PlayableBoard(cells.toMap())
