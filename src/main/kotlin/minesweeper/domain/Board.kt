package minesweeper.domain

class Board(
    val cells: Cells,
) {
    private var status: Status = Status.PLAYING

    fun open(position: Position) {
        if (isGameOver()) {
            return
        }

        when (cells.open(position)) {
            is Mine -> lose()
            is Normal -> winIfAllOpened()
        }
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

    private fun allOpened(): Boolean {
        return cells.allOpened()
    }

    enum class Status(
        val isFinished: Boolean = false,
    ) {
        WIN(true), LOSE(true), PLAYING,
    }
}
