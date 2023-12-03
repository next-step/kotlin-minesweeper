package minesweeper.model.vison

import minesweeper.model.point.Coordinate
import minesweeper.model.point.Vision

interface VisionStrategy {
    fun toData(): Map<Coordinate, Vision>
}
