package minesweeper.domain.coordinate

import minesweeper.common.value.CoordinateValue
import minesweeper.domain.Position

class BaseCoordinateSystem(
    height: Int,
    width: Int
) : CoordinateSystem {
    override val coordinate: List<Position>
        get() = field.toList()

    init {
        val list = mutableListOf<Position>()
        for (y in 0 until height) {
            for (x in 0 until width) {
                list.add(Position(x = CoordinateValue(value = x), y = CoordinateValue(value = y)))
            }
        }
        this.coordinate = list.toList()
    }

    override val height: Int = coordinate.filter { it.x == CoordinateValue(value = 0) }.size
    override val width: Int = coordinate.filter { it.y == CoordinateValue(value = 0) }.size
}
