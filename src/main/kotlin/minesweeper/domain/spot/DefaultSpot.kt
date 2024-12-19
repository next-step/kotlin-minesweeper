package minesweeper.domain.spot

import minesweeper.domain.Point

class DefaultSpot(point: Point) : Spot(point) {
    override fun toString(): String {
        return "C"
    }
}
