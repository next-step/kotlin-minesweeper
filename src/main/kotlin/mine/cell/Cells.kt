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

    fun findCell(position: Position): Cell = values.first { it.position == position }

    private fun isMineCell(position: Position): Boolean = values.filterIsInstance<MineCell>().any {
        it.isSamePosition(position)
    }

    private fun findMineCell(cell: Cell): Int = cell.aroundPosition().map { isMineCell(it) }.count { it }

    fun checkAroundMineCount() = values
        .filterIsInstance<NoneCell>()
        .forEach { cell ->
            cell.changeAroundCount(findMineCell(cell))
        }

    companion object {
        fun createCells(width: Width, height: Height, mineCount: Int): Cells {
            val size = width.value * height.value
            return List(size) { index: Int ->
                val x = (index / width.value)
                val y = (index % height.value)
                Position(x, y)
            }.shuffled()
                .mapIndexed { index, position ->
                    when {
                        index < mineCount -> MineCell(position)
                        else -> NoneCell(position)
                    }
                }.let(::Cells)
        }
    }
}
