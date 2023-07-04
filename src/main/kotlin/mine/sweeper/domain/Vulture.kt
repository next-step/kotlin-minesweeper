package mine.sweeper.domain

import mine.sweeper.PositionManager
import mine.sweeper.domain.value.MineCount

class Vulture(mapSize: MapSize) {

    private val positionManager = PositionManager(mapSize)

    fun findMinesPosition(mineCount: MineCount): Set<Position> {
        return positionManager.takePositionBy(mineCount)
    }
}
