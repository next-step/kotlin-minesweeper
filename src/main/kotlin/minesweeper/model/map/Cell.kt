package minesweeper.model.map

import minesweeper.model.map.coordinate.Coordinate
import minesweeper.model.map.coordinate.Position

sealed class Cell(open val position: Position) : Coordinate by position {

    data class Mine(override val position: Position) : Cell(position)

    data class Safe(override val position: Position) : Cell(position)
}
