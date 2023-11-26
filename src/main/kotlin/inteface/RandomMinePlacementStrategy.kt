package inteface

import domain.Position

class RandomMinePlacementStrategy : MinePlacementStrategy {
    override fun placeMines(height: Int, width: Int, mineCount: Int): List<Position> {
        val allPositions = (0 until height).flatMap { y -> (0 until width).map { x -> Position(x, y) } }
        return allPositions.shuffled().take(mineCount)
    }
}
