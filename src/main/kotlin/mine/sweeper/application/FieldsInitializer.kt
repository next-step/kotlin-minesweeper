package mine.sweeper.application

import mine.sweeper.domain.Field
import mine.sweeper.domain.GameOption
import mine.sweeper.domain.MineField
import mine.sweeper.domain.SafeField

class FieldsInitializer(
    private val option: GameOption
) {
    fun create(): MutableList<Field> {
        val minePositions = option.minePositions
        return MutableList(option.area) { index ->
            val position = option.positionBy(index)
            minePositions.find { it == position }?.let { MineField(it) } ?: SafeField(position)
        }
    }
}
