package mine.sweeper.domain

import mine.sweeper.domain.value.SurroundDirection

class GameInitializer(
    private val option: GameOption
) {
    fun create(): Game {
        val minePositions = option.minePositions

        val fieldList: MutableList<Field> = MutableList(option.area) { index ->
            val position = option.positionBy(index)
            minePositions.find { it == position }?.let { MineField(it) } ?: SafeField(position)
        }

        val surroundIncrementor = SurroundIncrementor(fieldList)
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
