package minesweeper.domain

data class Cell(
    val coordinate: Coordinate,
    val hasMine: Boolean = false,
) {
    fun isItCoordinate(coordinate: Coordinate): Boolean {
        return this.coordinate.itIt(coordinate)
    }

    fun isLastX(lastX: Int): Boolean {
        return coordinate.isX(lastX)
    }
}
