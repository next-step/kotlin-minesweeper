package minesweeper.domain

class MineLocationValidator {

    fun isDuplicatedMineLocation(board: Array<Array<Char>>, mineLocation: MineLocation): Boolean {
        val (x, y) = mineLocation.location
        return board[x][y] == '*'
    }


}
