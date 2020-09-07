package minesweeper.domain.squarestate

data class Empty(override val mineCount: Int = 0) : SquareState {
    override val isMine: Boolean = false

    override fun updateMineCount(count: Int): SquareState = this.copy(mineCount = count)

    override fun toString(): String = mineCount.toString()

    companion object {
        val default = Empty()
    }
}
