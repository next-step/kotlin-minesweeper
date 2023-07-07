package domain

class MineSweeperBoard(val width: Int, val height: Int, private val mines: Int) {
    private val board = Array(width * height) { Cell() }

    init {
        setMines()
    }

    private fun setMines() {
        (0 until width * height).shuffled().take(mines).forEach {
            board[it].isMine = true
        }
    }
}
