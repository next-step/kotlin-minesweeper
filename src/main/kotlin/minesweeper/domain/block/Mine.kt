package minesweeper.domain.block

data class Mine(private val position: Position, private val mineNearCount: Int? = null) : Block {

    override fun getPosition(): Position {
        return position
    }

    override fun getMineNearCount(): Int? {
        return mineNearCount
    }

    override fun hasVisited(): Boolean {
        return false
    }
}
