package mine.sweeper.domain

import mine.sweeper.PositionManager
import mine.sweeper.domain.value.Field

class SweeperMap(
    private val fields: Fields,
    private val positionManager: PositionManager
) {
    fun entireMap(): List<List<Field>> {
        return fields.entire()
    }

    fun setMineToRandomPosition() {
        val randomPosition = positionManager.randomPosition()
        fields.changeMineField(randomPosition)
    }
}
