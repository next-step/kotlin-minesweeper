package minesweeper.domain

class RandomCoordinateGenerator {

    fun generate(x: Int, y: Int, size: Int): List<Coordinate> {
        val coordinates = arrayListOf<Coordinate>()
        for (i in 0 until x) {
            for (j in 0 until y) {
                coordinates.add(Coordinate(i, j))
            }
        }
        coordinates.shuffle()
        return coordinates.subList(0, size)
    }
}
