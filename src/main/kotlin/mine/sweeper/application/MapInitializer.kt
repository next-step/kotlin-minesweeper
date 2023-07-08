package mine.sweeper.application

import mine.sweeper.domain.Field
import mine.sweeper.domain.Fields
import mine.sweeper.domain.MineField
import mine.sweeper.domain.SafeField
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.SurroundDirection
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class MapInitializer(
    private val mapSize: MapSize,
    private val minePositions: Set<Position>
) {
    fun create(): MineSweeperMap {
        val fieldList: MutableList<Field> = MutableList(mapSize.area) { index ->
            val x = index / mapSize.width.value
            val y = index % mapSize.width.value
            if (minePositions.contains(Position(x, y))) {
                MineField(Position(x, y))
            } else {
                SafeField(Position(x, y))
            }
        }
        val surroundIncrementor = SurroundIncrementor(fieldList)
        minePositions.forEach(surroundIncrementor::increase)
        return MineSweeperMap(Fields(fieldList), MineCount(minePositions.size))
    }

    private inner class SurroundIncrementor(private val fieldList: List<Field>) {
        fun increase(position: Position) {
            for (surround in SurroundDirection.values()) {
                val target = Position(position.x + surround.x, position.y + surround.y)
                val safeField = fieldList.find { it.isSame(target) } as? SafeField ?: continue
                safeField.increase()
            }
        }
    }
}
