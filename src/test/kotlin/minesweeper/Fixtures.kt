package minesweeper

import minesweeper.domain.Coordinate
import minesweeper.domain.OpenedCell
import minesweeper.domain.UndetonatedMineCell
import minesweeper.domain.UnopenedCell

fun undetonatedMineCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UndetonatedMineCell()

fun detonatedMineCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UndetonatedMineCell()

fun unOpenedCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UnopenedCell()

fun openedCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to OpenedCell()
