package mineswipper.domain.map.util

import mineswipper.domain.map.Position
import mineswipper.domain.map.Size

class RandomMinePositionGenerator : MinePositionStrategy {
    override fun createMinePosition(size: Size, mineAmount: Int): List<Position> {
        val positions = mutableSetOf<Position>()

        while (positions.size < mineAmount) {
            val x = (0 until size.width).random()
            val y = (0 until size.height).random()
            positions.add(Position(x, y))
        }

        return positions.toList()
    }
}
