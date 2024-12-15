package domain

class BoardCreator(
    private val minePlacer: MinePlacer,
) {
    fun create(
        height: Int,
        width: Int,
        mineCount: Int,
    ): Board {
        val board = Board(height, width, mineCount)
        this.initializeCells(board)
        this.initializeMines(board)
        return board
    }

    private fun initializeCells(board: Board): Board {
        for (i in 1..board.height) {
            for (j in 1..board.width) {
                val cell = Cell.create(i, j)
                board.addCell(cell)
            }
        }
        return board
    }

    private fun initializeMines(board: Board): Board {
        for (i in 1..board.mineCount) {
            minePlacer.addMine(board.cells)
        }
        return board
    }
}
