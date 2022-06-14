package minesweeper.model.map.coordinate

interface CellCoordinate {
    val row: Int
    val column: Int
}

data class Position(override val row: Int, override val column: Int) : CellCoordinate
