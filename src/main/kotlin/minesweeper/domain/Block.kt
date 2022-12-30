package minesweeper.domain

sealed class Block {
    abstract fun open()
}

class EmptyBlock : Block() {
    override fun open() {
        TODO("Not yet implemented")
    }
}

class MineBlock : Block() {
    override fun open() {
        TODO("Not yet implemented")
    }
}

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
