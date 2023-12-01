package minesweeper.domain

class MineSweeper(val gameBoard: GameBoard, mineCount: Int) {
    private val mineGenerator = MineGenerator(gameBoard, mineCount)
    private val minePoints = mineGenerator.generateRandomPoints()

    init {
        require(mineCount > 0) {
            "자연수를 입력해주세요."
        }
        placeMines(gameBoard)
    }

    private fun placeMines(gameBoard: GameBoard) {
        for (point in minePoints) {
            gameBoard.board[point.x][point.y].isMine = true
        }
    }
}
