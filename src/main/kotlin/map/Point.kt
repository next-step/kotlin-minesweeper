package map

import cell.Cell
import cell.Element
import mine.Mine

data class Point(
    val point: Pair<Index?, Index?>,
    val element: Element = Cell.ready(),
) {
    private val rowIndex: Index?
        get() {
            return point.first
        }

    private val columnIndex: Index?
        get() {
            return point.second
        }

    fun updateWithAdjacentMineCount(countMines: (rowIndex: Index?, columnIndex: Index?) -> Int): Point =
        update(countMines(rowIndex, columnIndex))

    private fun update(element: Int): Point =
        Point(
            point = this.point,
            element = this.element.updateValue(newValue = element.toString()),
        )

    fun isMine(): Boolean = element is Mine
}
