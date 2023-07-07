package mine.sweeper.application

import mine.sweeper.domain.Fields
import mine.sweeper.domain.value.MineCount
import mine.sweeper.view.dto.Position

class MapInitializer(private val fields: Fields) {
    fun create(minePositions: Set<Position>): MineSweeperMap {
        minePositions.forEach {
            fields.setMine(it)
            SurroundFieldManager.increase(it, fields)
        }
        return MineSweeperMap(fields, MineCount(minePositions.size))
    }
}
