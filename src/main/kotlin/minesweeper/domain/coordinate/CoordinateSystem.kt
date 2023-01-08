package minesweeper.domain.coordinate

import minesweeper.common.value.CoordinateValue
import minesweeper.domain.Position

sealed interface CoordinateSystem {
    val height: Int
    val width: Int
    val maxX: CoordinateValue
    val maxY: CoordinateValue
    val minX: CoordinateValue
    val minY: CoordinateValue
    val coordinate: List<Position>
}
