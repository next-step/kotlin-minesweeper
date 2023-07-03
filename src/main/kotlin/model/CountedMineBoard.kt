package model

import model.minemark.MineMark
import model.minemark.Safety

data class CountedMineBoard(val mineBoard: MineBoard) {
    val maxXPosition: Int by lazy { mineBoard.maxXPosition }
    val maxYPosition: Int by lazy { mineBoard.maxYPosition }

    init {
        require(mineBoard.doesNotContainsMark(NOT_ALLOWED_MARK)) {
            "mineBoard must not contain $NOT_ALLOWED_MARK mark. but provided `$mineBoard`"
        }
    }

    fun mark(position: Position): MineMark {
        return mineBoard.mark(position)
    }

    fun opened(positions: Collection<Position>): CountedMineBoard {
        return CountedMineBoard(mineBoard.opened(positions))
    }

    companion object {
        private val NOT_ALLOWED_MARK = Safety()
    }
}
