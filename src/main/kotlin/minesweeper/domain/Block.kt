package minesweeper.domain

sealed class Block

class EmptyBlock : Block()

sealed class OpenableBlcok : Block() {

    abstract fun open()
}

class MineBlock : OpenableBlcok() {
    override fun open() {
        TODO("Not yet implemented")
    }
}

class SafeBlock(val nearMineCount: Int = 0) : OpenableBlcok() {

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
