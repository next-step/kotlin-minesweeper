package minesweeper

import minesweeper.domain.ClosedEmptyCell
import minesweeper.domain.Coordinate
import minesweeper.domain.DetonatedMineCell
import minesweeper.domain.OpenedEmptyCell
import minesweeper.domain.UndetonatedMineCell

fun undetonatedMineCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UndetonatedMineCell()

fun detonatedMineCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to DetonatedMineCell()

fun closedEmptyCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to ClosedEmptyCell()

fun openedEmptyCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to OpenedEmptyCell()
