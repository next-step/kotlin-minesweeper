package mine.sweeper.domain

import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width

class RandomGameOption(
    height: Height,
    width: Width,
    mineCount: MineCount
) : GameOption {
    init {
        require(height.value * width.value > mineCount.value)
    }

    override val area = height.value * width.value

    override val widthVal = width.value

    private val randomPositions = MutableList(area) { positionBy(it) }.shuffled().toMutableList()

    override val minePositions = randomPositions.take(mineCount.value).toSet()
}
