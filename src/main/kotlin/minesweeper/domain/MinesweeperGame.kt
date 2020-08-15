package minesweeper.domain

class MinesweeperGame(height: String, width: String, minCount: String) {
    var minesweeperBoard: MinesweeperBoard
        private set

    init {
        val boardSize = BoardSize(height, width)
        val mineCount = MineNumber(minCount, boardSize)
        minesweeperBoard = MinesweeperBoard(boardSize, mineCount)
    }
}
