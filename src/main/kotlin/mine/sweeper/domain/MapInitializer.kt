package mine.sweeper.domain

import mine.sweeper.PositionManager

class MapInitializer(private val mapSize: MapSize) {
    private val positionManager = PositionManager(mapSize)

    fun createMap(): SweeperMap {
        val fields = Fields(mapSize)
        return SweeperMap(fields, positionManager)
    }
}
