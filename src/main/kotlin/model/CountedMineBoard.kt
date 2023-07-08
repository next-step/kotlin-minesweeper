package model

import model.minemark.MineMark
import model.minemark.Safety

data class CountedMineBoard(val filledElements: FilledElements) {
    val maxXPosition: Int by lazy { filledElements.maxXPosition }
    val maxYPosition: Int by lazy { filledElements.maxYPosition }
    val isClosedMineCountAny: Boolean by lazy { filledElements.isClosedMineCountAny }
    val isClosedMineAll: Boolean by lazy { filledElements.isClosedMineAll }

    init {
        require(filledElements.doesNotContainsMark(NOT_ALLOWED_MARK)) {
            "mineBoard must not contain $NOT_ALLOWED_MARK mark. but provided `$filledElements`"
        }
    }

    fun mark(position: Position): MineMark {
        return filledElements.mark(position)
    }

    fun isMineCount(position: Position): Boolean {
        return filledElements.mark(position).isMineCount
    }

    fun openedMineBoard(positions: Collection<Position>): CountedMineBoard {
        return CountedMineBoard(
            filledElements.replacedMarkElements(
                containsPositionOpenedMarkMapper(positions)
            )
        )
    }

    private fun containsPositionOpenedMarkMapper(positions: Collection<Position>): (Position, MineMark) -> MineMark =
        { position, mineMark ->
            if (positions.contains(position))
                mineMark.openedMark
            else
                mineMark
        }

    companion object {
        private val NOT_ALLOWED_MARK = Safety()
    }
}
