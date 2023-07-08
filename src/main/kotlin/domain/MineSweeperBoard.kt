package domain

class MineSweeperBoard(val boardSize: BoardSize, private val mines: Int) {
    private val board = Array(boardSize.width * boardSize.height) { Cell() }

    init {
        setMines()
    }

    private fun setMines() {
        (0 until boardSize.width * boardSize.height).shuffled().take(mines).forEach {
            board[it].setMine()
        }
    }

    override fun toString(): String {
        repeat(boardSize.height) { y ->
            board.slice(y * boardSize.width until (y + 1) * boardSize.width)
                .joinToString(" ") { it.toString() }.also {
                    println(it)
                }
        }
        return ""
    }
}
