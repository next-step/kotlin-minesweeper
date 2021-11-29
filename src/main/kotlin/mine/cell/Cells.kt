package mine.cell

import mine.Height
import mine.Width

/**
 * 셀 전체 관리
 * */
class Cells(val cellList: List<Cell>) {
    fun row(): Int = cellList.maxOf { it.position.x }
    fun column(): Int = cellList.maxOf { it.position.y }

    fun rowOfCells(row: Int): List<Cell> = cellList.filter { it.position.x == row }.sortedBy { it.position.y }

    companion object {
        fun createCells(width: Width, height: Height): Cells {
            val size = width.value * height.value
            return List(size) { index: Int ->
                val x = (index / width.value)
                val y = (index % height.value)
                Cell(Position(x, y))
            }.let(::Cells)
        }
    }
}
