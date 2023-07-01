package mine.sweeper

import mine.sweeper.domain.MapSize
import mine.sweeper.domain.value.Position

class PositionManager(mapSize: MapSize) {

    private val width = mapSize.width()

    private val randomPositions = MutableList(mapSize.area()) {
        Position(it / width, it % width)
    }.shuffled().toMutableList()

    fun randomPosition(): Position {
        check(randomPositions.isNotEmpty())
        return randomPositions.removeFirst()
    }
}
