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
        check(isNotGameOver()) { "게임이 종료되었습니다." }

        if (isMine(position)) {
            lose()
            return
        }

        val cell = cells[position].also { it.open() }
        cell.open()

        val adjacentNormalCells = getAdjacentNormalCells(cell)
        open(adjacentNormalCells)
        openAdjacentNormalCells(adjacentNormalCells)
    }

    private fun getAdjacentNormalCells(cell: Cell): List<Normal> {
        return cell.position.getAdjacentPositions(thresholdWidth = width, thresholdHeight = height)
            .filter { !isMine(it) }
            .map { cells[it] as Normal }
            .filter { !it.isOpened }
    }

    private fun open(adjacentNormalCells: List<Normal>) {
        adjacentNormalCells
            .forEach { it.open() }
    }

    private fun openAdjacentNormalCells(adjacentNormalCells: List<Normal>) {
        adjacentNormalCells
            .filter { it.adjacentMineCount == 0 }
            .forEach { open(it.position) }
    }

    fun win() {
        status = Status.WIN
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

    fun isNotGameOver(): Boolean {
        return status == Status.PLAYING
    }

    private fun isMine(position: Position): Boolean {
        return minePositions.contains(position)
    }

    private operator fun List<List<Cell>>.get(position: Position): Cell {
        return this[position.y][position.x]
    }

    enum class Status {
        WIN,
        LOSE,
        PLAYING,
    }
}
