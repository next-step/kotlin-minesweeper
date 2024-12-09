package map

import cell.Cell
import cell.Element

class Columns(
    val columns: MutableList<Point>,
) {
    fun updatePoints(countMines: (Index?, Index?) -> Int): Columns {
        val updatedPoints =
            columns
                .map { it.updateWithAdjacentMineCount(countMines) }
                .toMutableList()
        return Columns(updatedPoints)
    }

    companion object {
        fun ready(
            width: Width,
            rowIndex: Index?,
            element: Element = Cell.ready(),
        ): Columns =
            Columns(
                columns =
                    MutableList(size = width.size) {
                        Point(
                            Pair(
                                rowIndex,
                                Index.create(value = it, maxSize = width.size),
                            ),
                            element,
                        )
                    },
            )
    }
}
