package mine.cell

import mine.Height
import mine.Width

/**
 * 셀 전체 관리
 * */
class Cells(val values: List<Cell>) {
    fun row(): Int = values.maxOf { it.position.x }
    fun column(): Int = values.maxOf { it.position.y }

    fun rowOfCells(row: Int): Cells =
        values.filter { it.position.x == row }.sortedBy { it.position.y }.let(::Cells)

    companion object {
        fun createCells(width: Width, height: Height): Cells {
            val size = width.value * height.value
            return List(size) { index: Int ->
                val x = (index / width.value)
                val y = (index % height.value)
                NoneCell(Position(x, y))
            }.let(::Cells)
        }
    }
}
