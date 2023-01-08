package minesweeper.domain.coordinate

import minesweeper.common.value.CoordinateValue
import minesweeper.domain.Position

class BaseCoordinateSystem(
    override val height: Int,
    override val width: Int
) : CoordinateSystem {
    override val maxX: CoordinateValue = CoordinateValue(height - 1)
    override val maxY: CoordinateValue = CoordinateValue(width - 1)
    override val minX: CoordinateValue = CoordinateValue(value = 0)
    override val minY: CoordinateValue = CoordinateValue(value = 0)

    override val coordinate: List<Position> = buildList {
        repeat(height) { y ->
            repeat(width) { x ->
                this.add(Position(x = CoordinateValue(value = x), y = CoordinateValue(value = y)))
            }
        }
    }
}
