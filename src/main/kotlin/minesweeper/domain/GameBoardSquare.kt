package minesweeper.domain

data class GameBoardSquare(private val squareValueType: SquareValueType) {

    fun isMine(): Boolean {
        return squareValueType == SquareValueType.MINE
    }
}
