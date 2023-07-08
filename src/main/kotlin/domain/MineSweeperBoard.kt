package domain

class MineSweeperBoard(val boardSize: BoardSize, private val mines: Int) {
    val board = Array(boardSize.width * boardSize.height) { Cell() }

    init {
        setMines()
    }

    private fun setMines() {
        (0 until boardSize.width * boardSize.height).shuffled().take(mines).forEach {
            board[it].setMine()
        }
    }
}
