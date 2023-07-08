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

        cells[position.y][position.x].open()
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

    enum class Status {
        WIN,
        LOSE,
        PLAYING,
    }
}
