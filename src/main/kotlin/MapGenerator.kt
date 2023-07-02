internal object MapGenerator {
    fun generate(width: Int, height: Int, mineCount: Int, isOpened: Boolean = false): GameMap {
        val field = List(height) { y -> MutableList<Tile>(width) { x -> NumberTile(Point.from(x, y), isOpened) } }
        createMines(width, height, mineCount, isOpened).forEach {
            val x = it.point.x
            val y = it.point.y
            field[y.value][x.value] = it
        }
        return GameMap(field).apply {
            updateField()
        }
    }

    private fun createMines(width: Int, height: Int, count: Int, isOpened: Boolean = false): List<Mine> {
        return RandomGenerator.points(width, height, count).map { Mine(it, isOpened) }
    }
}
