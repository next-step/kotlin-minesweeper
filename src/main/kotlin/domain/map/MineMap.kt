package domain.map

import domain.cell.Cell

class MineMap(
    private val cells: MutableList<MutableList<Cell>>,
) {

    fun capture(): MapCapture {
        return MapCapture(cells.map { it.toList() })
    }
}
