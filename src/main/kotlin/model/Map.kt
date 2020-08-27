package model

import model.cell.Cell
import model.cell.Cells
import model.cell.MineType
import model.cell.Position

class Map(val x: Int, val y: Int, val mine: Int) {
    private val cells: Cells =
        Cells(createDefaultMap(x, y))
            .apply {
                createRandomMines(mine)
                checkMines(x, y)
            }

    private fun createDefaultMap(width: Int, height: Int): List<Cell> {
        return (0 until width).flatMap { x ->
            (0 until height).map { y ->
                Cell(MineType.NOT_MINE, Position(x, y))
            }
        }.toList()
    }

    override fun toString(): String {
        return cells.toString()
    }
}
