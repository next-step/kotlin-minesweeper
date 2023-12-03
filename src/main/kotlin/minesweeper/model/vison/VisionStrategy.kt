package minesweeper.model.vison

import minesweeper.model.point.Coordinate

interface VisionStrategy {
    fun toData(): Set<Coordinate>
}
