package map

import cell.Cell
import cell.Element

data class Point(
    val point: Pair<Index, Index>,
    val element: Element = Cell,
)
