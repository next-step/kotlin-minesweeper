package minesweeper.domain

class Land(
    mines: Mines,
    val point: Point,
    private var isOpened: Boolean = false,
) {
    val aroundMineCount: Int

    init {
        aroundMineCount = countAroundMines(mines)
    }

    fun isOpened(): Boolean = isOpened

    fun open() {
        isOpened = true
    }

    private fun countAroundMines(mines: Mines): Int = Direction.applyTo(point).count { point -> Mine(point) in mines }
}
