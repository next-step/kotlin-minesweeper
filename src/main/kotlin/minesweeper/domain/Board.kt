package minesweeper.domain

class Board(
    val cells: List<Cells>,
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

    fun win() {
        status = Status.WIN
    }

    fun isWin(): Boolean {
        return status == Status.WIN
    }

    enum class Status {
        WIN,
        LOSE,
        PLAYING,
    }
}
