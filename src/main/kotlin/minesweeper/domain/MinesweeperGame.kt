package minesweeper.domain

class MinesweeperGame(height: String, width: String, mineCount: String) {
    var minesweeperBoard: MinesweeperBoard
        private set
    var playState: PlayState = PlayState.READY
        private set

    init {
        val boardSize = BoardSize(height, width)
        minesweeperBoard = MinesweeperBoard(boardSize, MineNumber(mineCount, boardSize))
    }

    fun openCell(positionString: String) {
        require(POSITION_REGULAR_EXPRESSION.matches(positionString)) { "X, Y 값을 각각 자연수 범위내로 입력하시고, 구분자는 ','를 입력해주세요" }
        playState = minesweeperBoard.openCell(Position.of(positionString, minesweeperBoard.boardSize))
        if (playState != PlayState.PLAYING) minesweeperBoard.openAll()
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
