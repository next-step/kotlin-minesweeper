package minesweeper.domain.squarestate

class Empty private constructor(override val mineCount: Int = 0) : SquareState {
    override val isMine: Boolean = false

    override fun updateMineCount(count: Int): SquareState = Empty(mineCount = count)

    override fun toString(): String = mineCount.toString()

    companion object {
        val default = Empty()
    }
}
