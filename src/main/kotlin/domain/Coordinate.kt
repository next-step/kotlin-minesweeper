package domain

data class Coordinate(val x: Row, val y: Column) {
    companion object {
        fun from(x: Int, y: Int): Coordinate {
            return Coordinate(Row(x), Column(y))
        }
    }
}
