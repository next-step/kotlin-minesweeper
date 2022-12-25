package minesweeper.domain

class MineBoard(
    val height: Int,
    val width: Int,
    val totalMineCount: Int) {

    private val buttons: Buttons = Buttons.of(height, width, totalMineCount)

    override fun toString(): String {
        return buttons.toString()
    }
}
