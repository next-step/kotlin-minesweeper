package minesweeper.domain

data class Board(val height: Int, val width: Int, val mineCount: Int) {
    private val positionRange = 0 until height * width
    val board: List<Cell> = init()

    private fun init(): List<Cell> {
        require(height > 0 && width > 0 && mineCount > 0) { INVALID_BOARD_INFO }
        return setMinesAndBlocks(getMinesPosition())
    }

    fun getMinesPosition(): List<Int> {
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

    companion object {
        private const val INVALID_BOARD_INFO = "높이, 너비, 지뢰 개수는 모두 양의 정수이어야 합니다."
    }
}
