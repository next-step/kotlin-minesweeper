package minesweeper.domain

class MineLocationValidator {

    fun validateMineLocation(board: Array<Array<Char>>, mineLocation: MineLocation): Boolean {
        isLocationInIndexOfBoard(board, mineLocation)
        return isDuplicatedMineLocation(board, mineLocation)
    }

    private fun isLocationInIndexOfBoard(board: Array<Array<Char>>, mineLocation: MineLocation) {
        val (x, y) = mineLocation.location
        require(x in (startIndexOfBoard..board.first().size)) { "생성된 지뢰의 x좌표는 게임판 범위에 포함되어야 합니다." }
        require(y in (startIndexOfBoard..board.size)) { "생성된 지뢰의 y좌표는 게임판 범위에 포함되어야 합니다." }
    }

    private fun isDuplicatedMineLocation(board: Array<Array<Char>>, mineLocation: MineLocation): Boolean {
        val (x, y) = mineLocation.location
        return board[y][x] == '*'
    }

    companion object {
        private const val startIndexOfBoard = 0
    }
}
