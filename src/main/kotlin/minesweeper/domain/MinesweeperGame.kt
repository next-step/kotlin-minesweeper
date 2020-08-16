package minesweeper.domain

class MinesweeperGame(height: String, width: String, mineCount: String) {
    var minesweeperBoard: MinesweeperBoard
        private set

    init {
        require(NUMBER_REGEX.matches(height)) { "숫자를 입력해주세요." }
        require(NUMBER_REGEX.matches(width)) { "숫자를 입력해주세요." }
        val boardSize = BoardSize(height, width)
        require(NUMBER_REGEX.matches(mineCount)) { "숫자를 입력해주세요." }
        minesweeperBoard = MinesweeperBoard(boardSize, MineNumber(mineCount, boardSize))
    }
}
