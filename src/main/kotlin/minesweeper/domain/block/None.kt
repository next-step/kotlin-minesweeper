package minesweeper.domain.block

data class None(private val position: Position, private val mineNearCount: Int = 0) : Block {

    override fun getPosition(): Position {
        return position
    }

    override fun getMineNearCount(): Int {
        return mineNearCount
    }

    fun updateBlock(mineNearCount: Int): None {
        return None(position, mineNearCount)
    }
}
