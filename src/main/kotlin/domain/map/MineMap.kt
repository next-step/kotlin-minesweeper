package domain.map

import domain.cell.Cell

class MineMap(
    private val cells: MutableList<MutableList<Cell>>,
) {

    fun capture(): MapCapture {
        return MapCapture(cells.map { it.toList() })
    }

    operator fun get(coordinate: Coordinate): Cell {
        return cells[coordinate.y][coordinate.x]
    }
}
