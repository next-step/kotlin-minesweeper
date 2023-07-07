package mine.sweeper.domain

import mine.sweeper.application.RandomPositionManager
import mine.sweeper.domain.value.MineCount
import mine.sweeper.view.dto.Position

class Vulture(
    private val positionManager: RandomPositionManager,
    private val mineCount: MineCount
) {
    val newMinePositions: Set<Position>
        get() = positionManager.takePositionBy(mineCount)
}
