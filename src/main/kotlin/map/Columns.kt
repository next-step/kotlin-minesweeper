package map

import element.Cell
import element.Element

class Columns(
    val points: MutableList<Point>,
) {
    fun updatePoints(countMines: (Index?, Index?) -> Int): Columns {
        val updatedPoints =
            points
                .map { it.updateWithAdjacentMineCount(countMines) }
                .toMutableList()
        return Columns(updatedPoints)
    }

    fun open(columnIndex: Index): Columns? =
        Columns(
            points
                .mapIndexed { index, point ->
                    if (index != columnIndex.value) point else point.tryOpen() ?: return null
                }.toMutableList(),
        )

    fun isOpenAdjacent(columnIndex: Index): Boolean = points[columnIndex.value].isOpenAdjacentCell()

    companion object {
        fun ready(
            width: Width,
            rowIndex: Index?,
            element: Element = Cell.ready(),
        ): Columns =
            Columns(
                points =
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
