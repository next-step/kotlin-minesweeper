package minesweeper

class MineLocationValidator {

    fun isDuplicatedMineLocation(board: Array<Array<Char>>, location: Location): Boolean {
        val (x, y) = location.location
        return board[x][y] == '*'
    }
}
