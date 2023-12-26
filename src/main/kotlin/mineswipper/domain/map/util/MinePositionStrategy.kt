package mineswipper.domain.map.util

import mineswipper.domain.map.position.Positions
import mineswipper.domain.map.position.Size

interface MinePositionStrategy {
    fun createMinePosition(size: Size, mineAmount: Int): Positions
}
