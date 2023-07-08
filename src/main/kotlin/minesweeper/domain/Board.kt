package minesweeper.domain

class Board(
    val cells: List<Cells>,
    private val minePositions: List<Position>,
) {
    private val allNormalCount: Int
    private var openedCount: Int = 0
    private var status: Status = Status.PLAYING

    private val height: Int
        get() = cells.size

    private val width: Int
        get() = cells.first().size

    init {
        validateHeightIsPositive()
        validateWidthIsPositive()
        validateSameWidth()

        allNormalCount = (height * width) - minePositions.size
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

        if (isMine(position)) {
            lose()
            return
        }

        val cell = (cells[position] as Normal).also { it.openAndIncreaseOpenedCount() }
        if (!cell.isAdjacentMineCountZero()) {
            winIfAllOpened()
            return
        }

        val adjacentNormalCells = getAdjacentNormalCells(cell)
        adjacentNormalCells.openIfAdjacentMineCountNotZero()
        adjacentNormalCells.openIfAdjacentMineCountZero()
        winIfAllOpened()
    }

    private fun getAdjacentNormalCells(cell: Cell): List<Normal> {
        return cell.position.getAdjacentPositions(thresholdWidth = width, thresholdHeight = height)
            .filter { !isMine(it) }
            .map { cells[it] as Normal }
            .filter { !it.isOpened }
    }

    private fun List<Normal>.openIfAdjacentMineCountNotZero() {
        filter { !it.isAdjacentMineCountZero() }
            .forEach { it.openAndIncreaseOpenedCount() }
    }

    private fun List<Normal>.openIfAdjacentMineCountZero() {
        filter { it.isAdjacentMineCountZero() }
            .forEach { open(it.position) }
    }

    private fun Cell.openAndIncreaseOpenedCount() {
        if (isOpened) {
            return
        }
        this.open()
        openedCount++
    }

    fun win() {
        status = Status.WIN
    }

    private fun winIfAllOpened() {
        if (allOpened()) {
            win()
        }
    }

    fun lose() {
        status = Status.LOSE
    }

    fun isWin(): Boolean {
        return status == Status.WIN
    }

    fun isLose(): Boolean {
        return status == Status.LOSE
    }

    fun isGameOver(): Boolean {
        return isWin() || isLose()
    }

    fun isNotGameOver(): Boolean {
        return !isGameOver()
    }

    private fun isMine(position: Position): Boolean {
        return minePositions.contains(position)
    }

    private fun allOpened(): Boolean {
        return openedCount == allNormalCount
    }

    private operator fun List<List<Cell>>.get(position: Position): Cell {
        return this[position.y][position.x]
    }

    enum class Status {
        WIN, LOSE, PLAYING,
    }
}
