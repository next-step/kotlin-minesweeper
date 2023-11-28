package business

class GameManager(private val mines: Mines, limit: OpenedCellsLimit) {
    private val openedCells = OpenedCells(limit)
    fun open(point: Point): GameStatus {
        if (mines.contains(point)) return GameStatus.GAME_OVER
        openedCells.add(point, mines)
        return isOver()
    }

    fun isOver(): GameStatus = if (openedCells.isAllOpened(mines.size())) GameStatus.WIN else GameStatus.CONTINUE
    fun doActionWithMines(function: (Mines) -> Unit) = function(mines)
    fun doActionWithMinesAndOpenedCells(function: (Mines, OpenedCells) -> Unit) = function(mines, openedCells.copy())

    companion object {
        fun of(height: Int, width: Int, mineCount: Int, mineGenerator: MineGenerator): GameManager {
            val mines = mineGenerator.generate(height, width, mineCount)
            val limit = OpenedCellsLimit(height, width)
            return GameManager(mines, limit)
        }
    }
}
