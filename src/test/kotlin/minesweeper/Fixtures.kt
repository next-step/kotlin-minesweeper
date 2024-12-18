package minesweeper

import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.ClosedEmptyCell
import minesweeper.domain.Coordinate
import minesweeper.domain.DetonatedMineCell
import minesweeper.domain.OpenedEmptyCell
import minesweeper.domain.PlayableBoard
import minesweeper.domain.PlayableGame
import minesweeper.domain.UndetonatedMineCell

fun undetonatedMineCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to UndetonatedMineCell

fun detonatedMineCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to DetonatedMineCell

fun closedEmptyCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to ClosedEmptyCell

fun openedEmptyCellOf(
    y: Int,
    x: Int,
) = Coordinate(y, x) to OpenedEmptyCell

fun playableGameOf(vararg cells: Pair<Coordinate, Cell>) = PlayableGame(PlayableBoard(cells.toMap()))

fun cellsOf(vararg cells: Pair<Coordinate, Cell>) = Cells(cells.toMap())
