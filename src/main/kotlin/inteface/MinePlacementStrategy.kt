package inteface

import domain.Position

fun interface MinePlacementStrategy {
    fun placeMines(height: Int, width: Int, mineCount: Int, excludedPositions: List<Position>): List<Position>
}
