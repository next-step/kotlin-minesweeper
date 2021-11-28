package minesweeper.domain

class Board(cells: Map<Position, Cell>) {

    private val _cells: MutableMap<Position, Cell> = cells.toMutableMap()

    val cells: Map<Position, Cell>
        get() = _cells.toMap()
}

