package minesweeper.domain

class MinesweeperGame(_height: String, _width: String, _mineCount: String) {
    var minesweeperBoard: MinesweeperBoard
        private set

    init {
        require(NUMBER_REGEX.matches(_height)) { "숫자를 입력해주세요." }
        require(NUMBER_REGEX.matches(_width)) { "숫자를 입력해주세요." }
        val boardSize = BoardSize(_height, _width)
        require(NUMBER_REGEX.matches(_mineCount)) { "숫자를 입력해주세요." }
        val mineCount = MineNumber(_mineCount, boardSize)
        minesweeperBoard = MinesweeperBoard(boardSize, mineCount)
    }
}
