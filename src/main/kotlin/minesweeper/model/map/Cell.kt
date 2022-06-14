package minesweeper.model.map

import minesweeper.model.map.coordinate.CellCoordinate
import minesweeper.model.map.coordinate.Position

sealed class Cell(open val position: Position) : CellCoordinate by position {

    data class MineCell(override val position: Position) : Cell(position)

    data class SafeCell(override val position: Position) : Cell(position)
}
