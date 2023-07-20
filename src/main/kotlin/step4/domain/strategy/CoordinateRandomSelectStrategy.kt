package step4.domain.strategy

import step4.domain.Cell
import step4.domain.Coordinate

class CoordinateRandomSelectStrategy : CoordinateSelectStrategy {
    override fun select(cells: Map<Coordinate, Cell>): Cell =
        cells.filterNot { it.value.isMine() }.values.random()
}
