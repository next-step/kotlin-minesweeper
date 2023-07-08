package domain

class MineSweeperBoard(val width: Int, val height: Int, private val mines: Int) {
    private val board = Array(width * height) { Cell() }

    init {
        setMines()
    }

    private fun setMines() {
        (0 until width * height).shuffled().take(mines).forEach {
            board[it].setMine()
        }
    }

    override fun toString(): String {
        repeat(height) { y ->
            board.slice(y * width until (y + 1) * width)
                .joinToString(" ") { it.toString() }.also {
                    println(it)
                }
        }
        return ""
    }
}
