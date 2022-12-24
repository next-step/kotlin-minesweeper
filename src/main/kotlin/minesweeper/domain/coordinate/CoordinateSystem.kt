package minesweeper.domain.coordinate

import minesweeper.domain.Position

interface CoordinateSystem {
    val height: Int
    val width: Int
    val coordinate: List<Position>
}
