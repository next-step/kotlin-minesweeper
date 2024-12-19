package minesweeper.domain.spot

import minesweeper.domain.Point

class MineSpot(point: Point) : Spot(point) {
    override fun displayCharacter(): String {
        return "*"
    }
}
