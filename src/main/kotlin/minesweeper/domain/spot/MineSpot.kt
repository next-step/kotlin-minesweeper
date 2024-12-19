package minesweeper.domain.spot

import minesweeper.domain.Point

class MineSpot(point: Point) : Spot(point) {
    override fun toString(): String {
        return "*"
    }
}
