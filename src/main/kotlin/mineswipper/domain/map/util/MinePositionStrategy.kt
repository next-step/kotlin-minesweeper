package mineswipper.domain.map.util

import mineswipper.domain.map.Position
import mineswipper.domain.map.Size

interface MinePositionStrategy {
    fun createMinePosition(size: Size, mineAmount: Int): List<Position>
}