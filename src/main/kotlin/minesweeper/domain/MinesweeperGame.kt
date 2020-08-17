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
            if (!NUMBER_REGEX.matches(height) || !NUMBER_REGEX.matches(width) || !NUMBER_REGEX.matches(mineCount)) {
                return MinesweeperGameResult.Error("숫자를 입력해주세요.")
            }
            return MinesweeperGameResult.Success(height, width, mineCount)
        }

        fun newInstance(height: String, width: String, mineCount: String): MinesweeperGame {
            return MinesweeperGame(height, width, mineCount)
        }
    }
}
