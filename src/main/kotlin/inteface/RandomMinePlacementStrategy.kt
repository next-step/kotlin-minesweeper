package inteface

import domain.Position

class RandomMinePlacementStrategy : MinePlacementStrategy {
    override fun placeMines(height: Int, width: Int, mineCount: Int, excludedPositions: List<Position>): List<Position> {
        val allPositions = (0 until height).flatMap { y -> (0 until width).map { x -> Position(x, y) } }
        val availablePositions = allPositions.filterNot { it in excludedPositions }
        return availablePositions.shuffled().take(mineCount)
    }
}
