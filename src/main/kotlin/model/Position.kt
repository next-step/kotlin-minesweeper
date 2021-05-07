package model

data class Position private constructor(val heightIndex: Int, val widthIndex: Int) {

    init {
        require(heightIndex >= 0 && widthIndex >= 0) { "Position 인덱스는 음수일 수 없습니다!" }
    }

    companion object {
        private const val INITIAL_SIZE = 100

        private val POSITIONS = List(INITIAL_SIZE) { it }.flatMap { index -> List(INITIAL_SIZE) { Position(index, it) } }.toSet()

        fun get(heightIndex: Int, widthIndex: Int): Position {
            return POSITIONS.find { it.heightIndex == heightIndex && it.widthIndex == widthIndex }
                ?: Position(heightIndex, widthIndex)
        }
    }
}
