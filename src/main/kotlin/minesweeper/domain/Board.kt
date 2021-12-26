package minesweeper.domain

data class Board(val height: Int, val width: Int, val mineCount: Int) {
    private val positionRange = 0 until height * width
    val board: List<Cell> = init()

    private fun init(): List<Cell> {
        return setMinesAndBlocks(getMinesPosition())
    }

    private fun getMinesPosition(): List<Int> {
        return positionRange.shuffled().take(mineCount)
    }

    fun setMinesAndBlocks(minesPosition: List<Int>): List<Cell> {
        val board = mutableListOf<Cell>()
        for (position in positionRange) {
            if (minesPosition.contains(position)) {
                board.add(Mine)
            } else {
                board.add(Block)
            }
        }
        return board
    }

    override fun toString(): String {
        return board.toString()
    }
}
