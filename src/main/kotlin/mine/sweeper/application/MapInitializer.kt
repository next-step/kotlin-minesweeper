package mine.sweeper.application

import mine.sweeper.application.value.MineCount
import mine.sweeper.domain.Fields
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class MapInitializer(mapSize: MapSize) {
    private val fields = Fields(mapSize)

    fun create(minePositions: Set<Position>): MineSweeperMap {
        minePositions.forEach {
            fields.setMine(it)
            SurroundFieldManager.increase(it, fields)
        }
        return MineSweeperMap(fields, MineCount(minePositions.size))
    }
}
