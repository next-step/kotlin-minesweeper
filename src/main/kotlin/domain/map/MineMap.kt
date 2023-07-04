package domain.map

import domain.cell.Cell

class MineMap(
    private val cells: MutableList<MutableList<Cell>>,
) {

    fun capture(): MapCapture {
        return MapCapture(cells.map { it.toList() })
    }

    fun open(coordinate: Coordinate) {
        val newCell = get(coordinate).open()
        set(coordinate, newCell)
    }

    operator fun get(coordinate: Coordinate): Cell {
        return cells[coordinate.y][coordinate.x]
    }

    private operator fun set(
        coordinate: Coordinate,
        cell: Cell,
    ) {
        cells[coordinate.y][coordinate.x] = cell
    }
}
