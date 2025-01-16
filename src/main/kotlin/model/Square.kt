package model

data class Square(
    val type: SquareType = SquareType.TILE,
    val mineCount: Int = 0,
    val isOpen: Boolean = false
) {
    fun withMineCount(mineCount: Int): Square {
        return this.copy(mineCount = mineCount)
    }

    fun open(): Square {
        return this.copy(isOpen = true)
    }
}
