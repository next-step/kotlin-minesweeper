package minesweeper.model.laying

import minesweeper.model.point.Coordinate

interface MineLayingStrategy {
    fun layingCoordinates(count: Int): List<Coordinate>
}
