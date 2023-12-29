package minesweeper.domain

private val x = arrayOf(-1, 0, 0, 1)
private val y = arrayOf(0, -1, 1, 0)

class MineSweeperGame(
    private val cellFinder: CellFinder,
    private val openPositions: MutableList<Position> = mutableListOf(),
) {

    fun open(position: Position) {
        if (cellFinder.find(position) == null) {
            return
        }

        openPositions.add(position)
        for (i in 0 until 4) {
            val nextPosition = position + Position(x[i], y[i])
            if (!isOpen(nextPosition) && !isMine(nextPosition)) {
                open(nextPosition)
            }
        }
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
        for (openPosition in openPositions) {
            if (isMine(openPosition)) {
                return true
            }
        }

        return cellFinder.size() == openPositions.size + mineCount.value
    }

    fun getAroundMinesCount(position: Position): Int {
        return cellFinder.getAroundMinesCount(position)
    }

    fun isWin(mineCount: Size): Boolean {
        return cellFinder.size() == openPositions.size + mineCount.value
    }
}
