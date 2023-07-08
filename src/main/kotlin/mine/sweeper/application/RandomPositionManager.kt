package mine.sweeper.application

import mine.sweeper.domain.value.MineCount
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class RandomPositionManager(mapSize: MapSize) {
    private val randomPositions = MutableList(mapSize.area) {
        Position(it / mapSize.width.value, it % mapSize.width.value)
    }.shuffled().toMutableList()

    fun takePositionBy(mineCount: MineCount): Set<Position> {
        check(randomPositions.size > mineCount.value)
        return randomPositions.take(mineCount.value).toSet()
    }
}
