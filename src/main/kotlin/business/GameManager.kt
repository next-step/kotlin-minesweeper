package business

class GameManager(val mines: Mines, openedCells: OpenedCells) {
    private val _openedCells = openedCells

    val openedCells: OpenedCells
        get() = _openedCells.copy()

    fun open(point: Point): Boolean {
        if (mines.contains(point)) {
            return false
        }
        _openedCells.add(point, mines)
        return true
    }

    fun isOver(): Boolean = _openedCells.isAllOpened(mines.size())

    companion object {
        fun of(height: Int, width: Int, mineCount: Int, mineGenerator: MineGenerator): GameManager {
            val mines = mineGenerator.generate(height, width, mineCount)
            val openedCells = OpenedCells(height, width)
            return GameManager(mines, openedCells)
        }
    }
}
