package minesweeper.domain.block

import minesweeper.domain.exception.ExceptionReason
import minesweeper.domain.exception.MineSweeperException

class SafeBlock(val nearMineCount: Int = 0) : Block() {

    init {
        if (nearMineCount !in nearMineRange) throw MineSweeperException(ExceptionReason.ILLEGAL_NEAR_MINE_RANGE)
    }

    override fun open() {
        TODO("Not yet implemented")
    }

    companion object {
        private val nearMineRange = (0..8)
    }
}
