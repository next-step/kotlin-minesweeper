package mineswipper.domain.map.util

import mineswipper.domain.map.position.Position
import mineswipper.domain.map.position.Size

interface MinePositionStrategy {
    fun createMinePosition(size: Size, mineAmount: Int): List<Position>
}