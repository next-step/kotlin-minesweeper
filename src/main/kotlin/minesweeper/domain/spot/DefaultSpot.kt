package minesweeper.domain.spot

import minesweeper.domain.Point

class DefaultSpot(point: Point) : Spot(point) {
    override fun displayCharacter(): String {
        return "C"
    }
}
