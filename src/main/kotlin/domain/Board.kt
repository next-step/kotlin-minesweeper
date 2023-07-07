package domain

class Board(
    val height: Int,
    val width: Int,
    val mineCount: Int,
) {
    private val board: MutableList<Space> = MutableList(height * width) { Space.Empty }

    init {
        plantMines()
    }

    fun board(): List<Space> {
        return board
    }

    private fun plantMines() {
        minePositions().forEach {
            board[it] = Space.Mine
        }
    }

    private fun minePositions(): List<Int> = (0..board.lastIndex).shuffled().take(mineCount)
}
