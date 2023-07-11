package minesweeper.domain

sealed class GameBoardSquare(val numOfNearByMine: Int) {
    abstract fun isMine(): Boolean
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameBoardSquare) return false

        return numOfNearByMine == other.numOfNearByMine
    }

    override fun hashCode(): Int {
        return numOfNearByMine
    }

    class MineSquare() : GameBoardSquare(0) {
        override fun isMine(): Boolean = true
    }

    class NumberSquare(numOfNearByMine: Int) : GameBoardSquare(numOfNearByMine) {
        override fun isMine(): Boolean = false

        companion object {
            fun createEmpty(): NumberSquare = NumberSquare(numOfNearByMine = 0)
        }
    }
}
