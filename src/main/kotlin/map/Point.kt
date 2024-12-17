package map

import element.Cell
import element.Element
import element.status.CellStatus
import element.status.NumberCell

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
        update(element = countMines(rowIndex, columnIndex), cellStatus = NumberCell())

    private fun update(
        element: Int,
        cellStatus: CellStatus,
    ): Point =
        Point(
            point = this.point,
            element = this.element.updateValue(newValue = element.toString(), newStatus = cellStatus),
        )

    fun tryOpen(): Point? =
        element
            .open()
            ?.let { this.copy(element = it) }

    fun isOpen(): Boolean = element.isOpenable()

    fun isMine(): Boolean = element.isMine()
}
