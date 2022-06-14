package minesweeper.model.map

import minesweeper.model.map.coordinate.Position

sealed interface Cell {

    val position: Position

    data class MineCell(override val position: Position) : Cell

    data class SafeCell(override val position: Position) : Cell
}
