package domain

class BoardInfoGenerator(
    private val boardSize: BoardSize,
    mineCount: Int,
    mineLocationStrategy: MineLocationStrategy = RandomMineLocationStrategy()
) {
    private val mineLocations = mineLocationStrategy.generateMineLocations(boardSize, mineCount)

    fun generate(): BoardInfo {
        val board = initBoard()

        for (i in 0 until boardSize.height) {
            for (j in 0 until boardSize.width) {
                if (mineLocations.contains(Point(i, j))) {
                    board[i][j] = Cell(MINE)
                }
            }
        }

        return BoardInfo(board.map { it.toList() })
    }

    private fun initBoard(): Array<Array<Cell>> {
        return Array(boardSize.width) { Array(boardSize.height) { Cell(NON_MINE) } }
    }
}
