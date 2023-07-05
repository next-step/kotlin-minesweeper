package mine.sweeper.domain

import mine.sweeper.application.PositionManager
import mine.sweeper.application.value.MineCount
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class Vulture(mapSize: MapSize) {

    private val positionManager = PositionManager(mapSize)

    fun findMinesPosition(mineCount: MineCount): Set<Position> {
        return positionManager.takePositionBy(mineCount)
    }
}
