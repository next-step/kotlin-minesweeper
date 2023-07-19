package step4.domain.strategy

import step4.domain.Cell
import step4.domain.Coordinate

fun interface CoordinateSelectStrategy {
    fun select(cells: Map<Coordinate, Cell>): Cell
}
