package domain

class Board(cells: List<Cell>) {
    private val _cells: MutableList<Cell> = cells.toMutableList()
    val cells: List<Cell>
        get() = _cells.toMutableList()

    fun isMineCell(coordinate: Coordinate): Boolean {
        return cells.find { it.coordinate == coordinate } is Mine
    }

    fun addAll(addCells: List<Cell>) {
        _cells.addAll(addCells)
    }
}
