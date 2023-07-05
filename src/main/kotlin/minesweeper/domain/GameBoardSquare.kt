package minesweeper.domain

class GameBoardSquare(private var squareValueType: SquareValueType) {
    fun isMine(): Boolean {
        return squareValueType == SquareValueType.MINE
    }

    fun insertMine() {
        squareValueType = SquareValueType.MINE
    }

    fun printValue(): Char {
        return squareValueType.value
    }
}
