package minesweeper.domain

class Board(
    val cells: Cells,
    private val minePositions: List<Position>,
) {
    private var status: Status = Status.PLAYING

    fun open(position: Position) {
        if (isGameOver()) {
            return
        }

        when (val cell = cells.open(position)) {
            is Mine -> lose()
            is Normal -> {
                if (cell.isAdjacentMineCountZero()) {
                    cell.openAdjacentNormalCells()
                }
                winIfAllOpened()
            }
        }
    }

    private fun Cell.openAdjacentNormalCells() {
        val adjacentNormalCells = getAdjacentNormalCells(this)
        adjacentNormalCells.openIfAdjacentMineCountNotZero()
        adjacentNormalCells.openIfAdjacentMineCountZero()
    }

    private fun getAdjacentNormalCells(cell: Cell): List<Normal> {
        return cell.position.getAdjacentPositions(thresholdWidth = cells.width, thresholdHeight = cells.height)
            .filter { !isMine(it) }
            .map { cells[it] as Normal }
            .filter { !it.isOpened }
    }

    private fun List<Normal>.openIfAdjacentMineCountNotZero() {
        filter { !it.isAdjacentMineCountZero() }
            .forEach { it.open() }
    }

    private fun List<Normal>.openIfAdjacentMineCountZero() {
        filter { it.isAdjacentMineCountZero() }
            .forEach { open(it.position) }
    }

    private fun winIfAllOpened() {
        if (allOpened()) {
            win()
        }
    }

    fun win() {
        status = Status.WIN
    }

    fun lose() {
        status = Status.LOSE
    }

    fun isGameOver(): Boolean {
        return status.isFinished
    }

    fun isNotGameOver(): Boolean {
        return !isGameOver()
    }

    fun isWin(): Boolean {
        return status == Status.WIN
    }

    fun isLose(): Boolean {
        return status == Status.LOSE
    }

    private fun isMine(position: Position): Boolean {
        return minePositions.contains(position)
    }

    private fun allOpened(): Boolean {
        return cells.allOpened()
    }

    enum class Status(
        val isFinished: Boolean = false,
    ) {
        WIN(true), LOSE(true), PLAYING,
    }
}
