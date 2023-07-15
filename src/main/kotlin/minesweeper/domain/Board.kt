package minesweeper.domain

class Board(
    val cells: List<Cells>,
    private val minePositions: List<Position>,
) {
    private var status: Status = Status.PLAYING

    private val height: Int
        get() = cells.size

    private val width: Int
        get() = cells.first().size

    init {
        validateHeightIsPositive()
        validateWidthIsPositive()
        validateSameWidth()
    }

    private fun validateHeightIsPositive() {
        require(height > 0) { "높이는 0보다 커야 합니다." }
    }

    private fun validateWidthIsPositive() {
        require(width > 0) { "너비는 0보다 커야 합니다." }
    }

    private fun validateSameWidth() {
        require(cells.all { it.hasSize(width) }) { "너비가 일정하지 않습니다." }
    }

    fun open(position: Position) {
        if (isGameOver()) {
            return
        }

        when (val cell = cells[position].open()) {
            is Mine -> lose()
            is Normal -> {
                if (cell.isAdjacentMineCountZero()) {
                    val adjacentNormalCells = getAdjacentNormalCells(cell)
                    adjacentNormalCells.openIfAdjacentMineCountNotZero()
                    adjacentNormalCells.openIfAdjacentMineCountZero()
                }
                winIfAllOpened()
            }
        }
    }

    private fun getAdjacentNormalCells(cell: Cell): List<Normal> {
        return cell.position.getAdjacentPositions(thresholdWidth = width, thresholdHeight = height)
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
        return cells.all { it.allOpened() }
    }

    private operator fun List<List<Cell>>.get(position: Position): Cell {
        return this[position.y][position.x]
    }

    enum class Status(
        val isFinished: Boolean = false,
    ) {
        WIN(true), LOSE(true), PLAYING,
    }
}
