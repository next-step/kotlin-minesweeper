package domain.map

data class Coordinate(
    val x: Int,
    val y: Int,
) {

    init {
        require(x >= 0 && y >= 0) {
            "coordinate must be greater than or equal to zero. your input : x - $x, y - $y"
        }
    }
}
