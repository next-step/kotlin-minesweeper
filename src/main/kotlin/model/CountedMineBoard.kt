package model

import model.minemark.MineMark
import model.minemark.Safety

data class CountedMineBoard(val mineBoard: MineBoard) {
    val maxXPosition: Int by lazy { mineBoard.maxXPosition }
    val maxYPosition: Int by lazy { mineBoard.maxYPosition }
    val isClosedMineCountAny: Boolean by lazy { mineBoard.isClosedMineCountAny }
    val isClosedMineAll: Boolean by lazy { mineBoard.isClosedMineAll }

    init {
        require(mineBoard.doesNotContainsMark(NOT_ALLOWED_MARK)) {
            "mineBoard must not contain $NOT_ALLOWED_MARK mark. but provided `$mineBoard`"
        }
    }

    fun mark(position: Position): MineMark {
        return mineBoard.mark(position)
    }

    fun isMineCount(position: Position): Boolean {
        return mineBoard.mark(position).isMineCount
    }

    fun openedMineBoard(positions: Collection<Position>): CountedMineBoard {
        return CountedMineBoard(mineBoard.openedPositionsMineBoard(positions))
    }

    companion object {
        private val NOT_ALLOWED_MARK = Safety()
    }
}
