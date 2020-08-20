package minesweeper.domain

class MinesweeperGame(height: String, width: String, mineCount: String) {
    var minesweeperBoard: MinesweeperBoard
        private set

    init {
        val boardSize = BoardSize(height, width)
        minesweeperBoard = MinesweeperBoard(boardSize, MineNumber(mineCount, boardSize))
    }

    companion object {
        fun requestGame(height: String, width: String, mineCount: String): MinesweeperGameResult {
            if (!NUMBER_REGEX.matches(height)) {
                return MinesweeperGameResult.InvalidHeight
            }
            if (!NUMBER_REGEX.matches(width)) {
                return MinesweeperGameResult.InvalidWidth
            }
            if (!NUMBER_REGEX.matches(mineCount)) {
                return MinesweeperGameResult.InvalidMineCount
            }
            return MinesweeperGameResult.Success(height, width, mineCount)
        }

        fun of(height: String, width: String, mineCount: String): MinesweeperGame {
            return MinesweeperGame(height, width, mineCount)
        }
    }
}
