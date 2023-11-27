package business

class GameManager(val mines: Mines, openedCells: OpenedCells) {
    private val _openedCells = openedCells

    val openedCells: OpenedCells
        get() = _openedCells.copy()

    fun open(point: Point): GameStatus {
        if (mines.contains(point)) return GameStatus.GAME_OVER
        _openedCells.add(point, mines)
        return isOver()
    }

    fun isOver(): GameStatus = if (_openedCells.isAllOpened(mines.size())) GameStatus.WIN else GameStatus.CONTINUE

    companion object {
        fun of(height: Int, width: Int, mineCount: Int, mineGenerator: MineGenerator): GameManager {
            val mines = mineGenerator.generate(height, width, mineCount)
            val openedCells = OpenedCells(height, width)
            return GameManager(mines, openedCells)
        }
    }
}
