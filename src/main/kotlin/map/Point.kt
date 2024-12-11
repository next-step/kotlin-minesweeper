package map

import cell.Cell
import cell.Element
import cell.showable.Hide
import cell.showable.Show
import cell.showable.Showable
import cell.status.CellStatus
import cell.status.MineCell
import cell.status.NumberCell
import mine.Mine

data class Point(
    val point: Pair<Index?, Index?>,
    val element: Element = Cell.ready(),
    val visibility: Showable = Hide,
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
        update(element = countMines(rowIndex, columnIndex), cellStatus = NumberCell)

    private fun update(
        element: Int,
        cellStatus: CellStatus,
    ): Point =
        Point(
            point = this.point,
            element = this.element.updateValue(newValue = element.toString(), newStatus = cellStatus),
        )

    fun open(): Point? =
        when {
            element.status is MineCell -> null
            element.status is NumberCell && visibility is Hide -> Point(point = this.point, element = this.element, visibility = Show)
            else -> this
        }

    fun isMine(): Boolean = element is Mine
}
