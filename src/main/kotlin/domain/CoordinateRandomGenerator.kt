package domain

class CoordinateRandomGenerator(
    private val coordinates: List<Coordinate>,
    private val landMine: Int
) : CoordinateGenerator {

    override fun coordinates(): List<Coordinate> {
        return coordinates.shuffled().take(landMine)
    }
}
