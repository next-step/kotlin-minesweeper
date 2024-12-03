package map

import cell.Cell
import cell.Element

data class Point(
    val point: Pair<Int, Int>,
    val element: Element = Cell,
)
