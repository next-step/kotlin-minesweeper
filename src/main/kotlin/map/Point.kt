package map

import cell.Cell
import cell.Element
import mine.Mine

data class Point(
    val point: Pair<Index?, Index?>,
    val element: Element = Cell.ready(),
) {
    val rowIndex: Index?
        get() {
            return point.first
        }

    val columnIndex: Index?
        get() {
            return point.second
        }

    fun update(element: String): Point =
        Point(
            point = this.point,
            element = this.element.updateValue(newValue = element),
        )

    fun isMine(): Boolean = element is Mine
}
