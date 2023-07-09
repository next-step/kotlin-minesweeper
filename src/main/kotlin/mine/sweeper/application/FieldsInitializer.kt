package mine.sweeper.application

import mine.sweeper.domain.Field
import mine.sweeper.domain.MineField
import mine.sweeper.domain.RandomGameOption
import mine.sweeper.domain.SafeField

class FieldsInitializer(private val option: RandomGameOption) {
    fun create(): MutableList<Field> {
        val minePositions = option.minePositions
        return MutableList(option.area) { index ->
            val position = option.positionBy(index)
            val minePosition = minePositions.find { it == position }
                ?: return@MutableList SafeField(position)
            MineField(minePosition)
        }
    }
}
