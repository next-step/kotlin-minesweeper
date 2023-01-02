package minesweeper.domain.coordinate

import minesweeper.common.value.CoordinateValue
import minesweeper.domain.Position

class BaseCoordinateSystem(
    height: Int,
    width: Int
) : CoordinateSystem {
    override val coordinate: List<Position> = buildList {
        repeat(height) { y ->
            repeat(width) { x ->
                this.add(Position(x = CoordinateValue(value = x), y = CoordinateValue(value = y)))
            }
        }
        this.toList()
    }
        get() = field.toList()

    override val height: Int = coordinate.filter { it.x == CoordinateValue(value = 0) }.size
    override val width: Int = coordinate.filter { it.y == CoordinateValue(value = 0) }.size
}
