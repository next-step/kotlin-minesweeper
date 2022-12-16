package domain

class LocationOfMines(
    private val mines: List<Coordinate>
) {
    fun exist(coordinate: Coordinate) = mines.contains(coordinate)
}
