package mine.sweeper.domain

import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Position
import mine.sweeper.domain.value.Width

class GameOption(
    height: Height,
    private val width: Width,
    mineCount: MineCount
) {
    init {
        check(height.value * width.value > mineCount.value)
    }

    val area = height.value * width.value

    private val randomPositions = MutableList(area) { positionBy(it) }.shuffled().toMutableList()

    val minePositions = randomPositions.take(mineCount.value).toSet()

    fun positionBy(index: Int): Position {
        val x = index / width.value
        val y = index % width.value
        return Position(x, y)
    }
}
