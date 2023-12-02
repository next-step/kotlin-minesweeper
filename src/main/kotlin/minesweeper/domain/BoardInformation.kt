package minesweeper.domain

class BoardInformation(
    val height: Height,
    val width: Width,
    val mineCount: MineCount
) {
    fun isMineCountSmallerThanBoardSize(): Boolean = mineCount.value < height.value * width.value
}
