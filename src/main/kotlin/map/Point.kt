package map

import cell.Cell
import cell.Element

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

    fun update(newElementValue: Char): Point =
        Point(
            point = this.point,
            element = element.updateValue(newValue = newElementValue),
        )
}
