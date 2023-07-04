package mine.sweeper

import mine.sweeper.domain.MapSize
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.Position

class PositionManager(mapSize: MapSize) {
    private val randomPositions = MutableList(mapSize.area()) {
        Position(it / mapSize.width.value, it % mapSize.width.value)
    }.shuffled().toMutableList()

    fun takePositionBy(mineCount: MineCount): Set<Position> {
        check(randomPositions.size > mineCount.value)
        return randomPositions.take(mineCount.value).toSet()
    }
}
