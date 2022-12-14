package domain

private const val MIN_VALUE = 0

class RandomFieldGenerator : FieldGenerator {
    override fun generate(width: Int, height: Int, mineCount: Int): Field {
        val rows = (MIN_VALUE until height).map {
            Row.create(MIN_VALUE until width)
        }

        val mineCoordinates = createMineCoordinates(height, width, mineCount)

        return Field(rows, mineCoordinates)
    }

    private fun createMineCoordinates(height: Int, width: Int, mineCount: Int) =
        (MIN_VALUE until height).flatMap { y ->
            (MIN_VALUE until width).map { x ->
                Coordinate(y, x)
            }
        }.shuffled().subList(0, mineCount)
}
