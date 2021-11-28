package mine.cell

import mine.Height
import mine.Width

class Cells(val cells: List<Cell>) {

    companion object {
        fun createCells(width: Width, height: Height): Cells {
            val size = width.value * height.value
            return List(size) { index: Int ->
                val x = (index / width.value)
                val y = (index % height.value)
                Cell(Position(x, y), "normal")
            }.let(::Cells)
        }
    }
}
