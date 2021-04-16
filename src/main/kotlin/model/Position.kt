package model

data class Position private constructor(val heightIndex: Int, val widthIndex: Int) {

    init {
        require(heightIndex >= 0 && widthIndex >= 0) { "Position 인덱스는 음수일 수 없습니다!" }
    }

    companion object {
        private val POSITIONS = mutableSetOf<Position>()

        fun get(heightIndex: Int, widthIndex: Int): Position {
            val position = Position(heightIndex, widthIndex)

            if (!POSITIONS.contains(position)) {
                POSITIONS.add(position)
            }

            return POSITIONS.find { it == position }!!
        }
    }
}
