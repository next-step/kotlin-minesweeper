package minesweeper.domain

class Coordinates(width: Int, length: Int) {
    val coordinates = makeCoordinate(width, length)

    private fun makeCoordinate(width: Int, length: Int): List<Coordinate> {
        return (FIRST until length).flatMap { y -> (FIRST until width).map { x -> Coordinate(x, y) } }
    }

    fun makeMineCoordinates(mines: Int): List<Coordinate> = coordinates.shuffled().take(mines)

    companion object {
        const val FIRST = 0
    }
}
