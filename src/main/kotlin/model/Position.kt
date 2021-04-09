package model

data class Position private constructor(val heightIndex: Int, val widthIndex: Int) {
    companion object {
        private val POSITIONS = mutableListOf<Position>()

        fun get(heightIndex: Int, widthIndex: Int): Position {
            val position = Position(heightIndex, widthIndex)

            if (!POSITIONS.contains(position)) {
                POSITIONS.add(position)
            }

            return POSITIONS.find { it == position }!!
        }
    }
}
