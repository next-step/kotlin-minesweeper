package minesweeper.domain.block

data class None(private val position: Position, private val mineNearCount: Int? = null) : Block {

    override fun getPosition(): Position {
        return position
    }

    override fun getMineNearCount(): Int? {
        return mineNearCount
    }

    fun updateBlock(mineNearCount: Int): None {
        return None(position, mineNearCount)
    }

    override fun hasVisited(): Boolean {
        return mineNearCount != null
    }

    override fun isMine(): Boolean {
        return false
    }
}
