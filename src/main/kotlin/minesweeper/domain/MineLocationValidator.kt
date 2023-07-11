package minesweeper.domain

object MineLocationValidator {

    private const val startIndexOfBoard = 0

    fun validateMineLocation(board: List<List<GameBoardSquare>>, squareLocation: SquareLocation): Boolean {
        isLocationInIndexOfBoard(board, squareLocation)
        return isDuplicatedMineLocation(board, squareLocation)
    }

    private fun isLocationInIndexOfBoard(board: List<List<GameBoardSquare>>, squareLocation: SquareLocation) {
        val (x, y) = squareLocation
        require(x in (startIndexOfBoard..board.first().size)) { "생성된 지뢰의 x좌표는 게임판 범위에 포함되어야 합니다." }
        require(y in (startIndexOfBoard..board.size)) { "생성된 지뢰의 y좌표는 게임판 범위에 포함되어야 합니다." }
    }

    private fun isDuplicatedMineLocation(board: List<List<GameBoardSquare>>, squareLocation: SquareLocation): Boolean {
        val (x, y) = squareLocation
        return board[y][x].isMine()
    }
}
