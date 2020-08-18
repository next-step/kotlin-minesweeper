package minesweeper.model

const val DEFAULT_POSITION = 0
const val NEXT_POSITION = 1

data class Board(val row: Int, val col: Int, val mineCount: Int) {
    val board: Array<Array<Block>> = Array(row) { Array(col) { Block() } }

    init {
        val minePositions = MineSweeperMaker().getMinePosition(row, col, mineCount)
        var position = DEFAULT_POSITION

        for (row in board) {
            for (block in row) {
                position += NEXT_POSITION

                if (minePositions.contains(position)) {
                    block.type = Type.MINE
                }
            }
        }
    }
}
