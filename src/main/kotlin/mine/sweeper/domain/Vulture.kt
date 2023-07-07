package mine.sweeper.domain

import mine.sweeper.application.RandomPositionManager
import mine.sweeper.domain.value.MineCount
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class Vulture(
    mapSize: MapSize,
    private val mineCount: MineCount,
    private val positionManager: RandomPositionManager = RandomPositionManager(mapSize)
) {
    fun findMinesPosition(): Set<Position> {
        return positionManager.takePositionBy(mineCount)
    }
}
