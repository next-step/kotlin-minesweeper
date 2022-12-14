package domain

private const val MIN_VALUE = 0

class RandomFieldGenerator : FieldGenerator {
    override fun generate(width: Int, height: Int, mineCount: Int): Field {
        val coordinates = createCoordinates(height, width)

        return Field.create(
            coordinates.shuffled().mapIndexed { index, coordinate ->
                if (index < mineCount) {
                    Mine(coordinate)
                } else {
                    Land(coordinate)
                }
            }
        )
    }

    private fun createCoordinates(height: Int, width: Int) =
        (MIN_VALUE until height).flatMap { y ->
            (MIN_VALUE until width).map { x ->
                Coordinate(y, x)
            }
        }
}
