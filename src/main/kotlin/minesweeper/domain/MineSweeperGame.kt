package minesweeper.domain

class MineSweeperGame(
    private val cellFinder: CellFinder,
    private val openPositions: MutableSet<Position> = mutableSetOf(),
) {

    fun open(position: Position) {
        if (cellFinder.find(position) == null) {
            return
        }

        openPositions.add(position)
        position.getAdjacent()
            .filter { !isOpen(it) && !isMine(it) }
            .forEach { open(it) }
    }

    fun isOpen(position: Position): Boolean {
        if (cellFinder.find(position) == null) {
            return false
        }
        return openPositions.contains(position)
    }

    fun isMine(position: Position): Boolean {
        if (cellFinder.find(position) == null) {
            return false
        }
        return cellFinder.isMine(position)
    }

    fun isFinished(mineCount: Size): Boolean {
        if (isLose()) {
            return true
        }

        return cellFinder.size() == openPositions.size + mineCount.value
    }

    fun getAroundMinesCount(position: Position): Int {
        return cellFinder.getAroundMinesCount(position)
    }

    fun isWin(mineCount: Size): Boolean {
        return cellFinder.size() == openPositions.size + mineCount.value
    }

    private fun isLose(): Boolean {
        return openPositions.any { isMine(it) }
    }
}
