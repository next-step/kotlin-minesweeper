package model

import model.cell.Cell
import model.cell.Cells
import model.cell.MineStatus
import model.cell.Position

class Map(val x: Int, val y: Int, val mine: Int) {
    private val cells: Cells = Cells(createDefaultMap(x, y))

    fun createMine(mine: Int) {
        cells.changeNotToMine(mine)
    }

    private fun createDefaultMap(width: Int, height: Int): MutableList<Cell> {
        return (0 until width).flatMap { x ->
            (0 until height).map { y ->
                Cell(MineStatus(false), Position(x, y))
            }
        }.toMutableList()
    }

    override fun toString(): String {
        return cells.toString()
    }
}
