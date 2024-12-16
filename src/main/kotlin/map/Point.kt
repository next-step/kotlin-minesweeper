package map

import element.Cell
import element.Element
import element.Mine
import element.showable.Hide
import element.showable.Show
import element.showable.Showable
import element.status.CellStatus
import element.status.MineCell
import element.status.NumberCell

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

    fun tryOpen(): Point? =
        when {
            element.status is MineCell -> null
            isNumberCell() && visibility is Hide -> this.open()
            else -> this
        }

    private fun open(): Point = this.copy(visibility = Show)

    fun isOpenAdjacentCell(): Boolean = this.isNumberCell() && this.isMineCountZero() && this.visibility == Hide

    private fun isMineCountZero(): Boolean = element.value?.toIntOrNull() == 0

    private fun isNumberCell(): Boolean = element.status is NumberCell

    fun isMine(): Boolean = element is Mine
}
