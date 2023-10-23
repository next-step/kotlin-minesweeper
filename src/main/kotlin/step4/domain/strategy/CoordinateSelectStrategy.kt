package step4.domain.strategy

import step4.domain.cell.Cell
import step4.domain.coordinate.Coordinate

fun interface CoordinateSelectStrategy {
    fun select(cells: Map<Coordinate, Cell>): Cell
}
