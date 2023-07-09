package mine.sweeper.application

import mine.sweeper.domain.Field
import mine.sweeper.domain.Fields
import mine.sweeper.domain.MineField
import mine.sweeper.domain.SafeField
import mine.sweeper.domain.value.Position
import mine.sweeper.domain.value.SurroundDirection

class GameInitializer(private val fieldList: List<Field>) {
    fun create(): Game {
        val surroundIncrementor = SurroundIncrementor(fieldList)
        val minePositions = fieldList.filterIsInstance<MineField>().map { it.position }.toSet()
        minePositions.forEach(surroundIncrementor::increase)
        return Game(Fields(fieldList))
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
