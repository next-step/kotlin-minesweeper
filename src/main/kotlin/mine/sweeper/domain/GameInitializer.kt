package mine.sweeper.domain

import mine.sweeper.domain.value.Field

class GameInitializer(
    private val option: GameOption
) {
    fun create(): Game {
        val minePositions = option.minePositions

        val fieldList: MutableList<Field> = MutableList(option.area) { index ->
            val position = option.positionBy(index)
            minePositions.find { it == position }?.let { Field(it, "mine") } ?: Field(position, "safe")
        }

        return Game(Fields(fieldList))
    }
}
