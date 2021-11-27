package minesweeper.model

class Board(
    val width: Width,
    val height: Height,
    val mineCount: MineCount
) {

    companion object {
        fun create(
            width: Width,
            height: Height,
            mineCount: MineCount = MineCount.ZERO
        ): Board = Board(width, height, mineCount)
    }
}
