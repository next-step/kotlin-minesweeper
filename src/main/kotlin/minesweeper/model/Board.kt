package minesweeper.model

const val DEFAULT_POSITION = 0

data class Board(val row: Int, val col: Int, val mineCount: Int) {
    val board: List<List<Block>> = List(row) { List(col) { Block() } }

    init {
        val minePositions = MineSweeperMaker().getMinePosition(row, col, mineCount)

        for (position in DEFAULT_POSITION until row * col) {
            turnInOrder(position, minePositions, board)
        }
    }

    private fun turnInOrder(position: Int, minePositions: List<Int>, board: List<List<Block>>) {
        minePositions.forEach {
            if (it == position) {
                board[(it - 1) / col][(it - 1) % col].type = Type.MINE
            }
        }
    }
}
