package minesweeper.domain

object MineLocationValidator {

    private const val startIndexOfBoard = 0

    fun validateMineLocation(board: List<List<GameBoardSquare>>, mineLocation: MineLocation): Boolean {
        isLocationInIndexOfBoard(board, mineLocation)
        return isDuplicatedMineLocation(board, mineLocation)
    }

    private fun isLocationInIndexOfBoard(board: List<List<GameBoardSquare>>, mineLocation: MineLocation) {
        val (x, y) = mineLocation.location
        require(x in (startIndexOfBoard..board.first().size)) { "생성된 지뢰의 x좌표는 게임판 범위에 포함되어야 합니다." }
        require(y in (startIndexOfBoard..board.size)) { "생성된 지뢰의 y좌표는 게임판 범위에 포함되어야 합니다." }
    }

    private fun isDuplicatedMineLocation(board: List<List<GameBoardSquare>>, mineLocation: MineLocation): Boolean {
        val (x, y) = mineLocation.location
        return board[y][x].isMine()
    }
}
