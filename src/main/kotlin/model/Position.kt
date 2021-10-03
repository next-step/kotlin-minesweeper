package model

data class Position private constructor(val heightIndex: Int, val widthIndex: Int) {
    init {
        require(heightIndex >= MINIMUM && widthIndex >= MINIMUM) { "Position 인덱스는 $MINIMUM 보다 작을 수 없습니다!" }
    }

    fun heightMinus(i: Int): Position {
        return copy(heightIndex = (heightIndex - i).coerceAtLeast(MINIMUM))
    }

    fun widthMinus(i: Int): Position {
        return copy(widthIndex = (widthIndex - i).coerceAtLeast(MINIMUM))
    }

    companion object {
        private const val MINIMUM = 0
        private const val INITIAL_SIZE = 100

        private val POSITIONS = List(INITIAL_SIZE) { it }.flatMap { index -> List(INITIAL_SIZE) { Position(index, it) } }.toSet()

        fun get(heightIndex: Int, widthIndex: Int): Position {
            return POSITIONS.find { it.heightIndex == heightIndex && it.widthIndex == widthIndex }
                ?: Position(heightIndex, widthIndex)
        }
    }
}
