package minesweeper.domain

class Land(
    mines: Mines,
    val point: Point,
    private var isOpen: Boolean = false,
) {
    val aroundMineCount: Int

    init {
        aroundMineCount = countAroundMines(mines)
    }

    fun open() {
        isOpen = true
    }

    private fun countAroundMines(mines: Mines): Int = Direction.applyTo(point).count { point -> Mine(point) in mines }
}
