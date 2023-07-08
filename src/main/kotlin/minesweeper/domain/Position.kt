package minesweeper.domain

@JvmInline
value class Position(val position: Int) {
    fun dec(): Int {
        return position.dec()
    }

    fun inc(): Int {
        return position.inc()
    }

    init {
        require(position > INVALID_POSITION) {
            "위치는 무조건 0보다 크거나 같아야한다"
        }
    }

    companion object {
        const val INVALID_POSITION = -1
        fun of(positionString: String): Position {
            val position = positionString.toIntOrNull()
            require(position != null) {
                "위치값은 숫자이어야함"
            }
            return Position(position - 1)
        }
    }
}
