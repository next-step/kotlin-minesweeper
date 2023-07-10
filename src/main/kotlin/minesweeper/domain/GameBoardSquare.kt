package minesweeper.domain

class GameBoardSquare(private var squareValueType: SquareValueType) {
    init {
        require(squareValueType == SquareValueType.EMPTY) { "게임판을 초기화 할 때는 지뢰가 되면 안됩니다." }
    }

    fun isMine(): Boolean {
        return squareValueType == SquareValueType.MINE
    }

    fun insertMine() {
        squareValueType = SquareValueType.MINE
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameBoardSquare

        return squareValueType == other.squareValueType
    }

    override fun hashCode(): Int {
        return squareValueType.hashCode()
    }
}
