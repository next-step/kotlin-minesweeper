package minesweeper.domain.spot

import minesweeper.domain.Point

abstract class Spot(val point: Point) {
    abstract fun displayCharacter(): String
}
