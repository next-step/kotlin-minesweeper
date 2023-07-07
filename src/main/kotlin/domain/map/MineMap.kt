package domain.map

import domain.cell.Cell

class MineMap(
    private val _cells: MutableList<MutableList<Cell>>,
) {

    val cells: List<List<Cell>>
        get() = _cells.map { it.toList() }

    fun open(coordinate: Coordinate) {
        val newCell = get(coordinate).open()
        set(coordinate, newCell)
    }

    fun isIn(coordinate: Coordinate): Boolean {
        val yDeadLine = _cells.lastIndex
        val xDeadLine = _cells.first().lastIndex
        return coordinate.y <= yDeadLine && coordinate.x <= xDeadLine
    }

    fun isAllGroundCellsOpened(): Boolean {
        return _cells.flatten()
            .filter { it.isGround() }
            .all { it.isOpen() }
    }

    fun getHideGroundCellOrNull(coordinate: Coordinate): Cell.Ground? {
        return when (val cell = get(coordinate)) {
            is Cell.Mine -> null
            is Cell.Ground -> cell.takeIf { it.isHide() }
        }
    }

    operator fun get(coordinate: Coordinate): Cell {
        return _cells[coordinate.y][coordinate.x]
    }

    private operator fun set(
        coordinate: Coordinate,
        cell: Cell,
    ) {
        _cells[coordinate.y][coordinate.x] = cell
    }
}
