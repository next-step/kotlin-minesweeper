package minesweeper.domain.plant_strategy

import minesweeper.domain.Point

interface PlantStrategy {
    fun createMines(width: Int, height: Int, mineCount: Int): Set<Point>
}
