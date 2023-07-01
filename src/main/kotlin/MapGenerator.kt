object MapGenerator {
    fun generate(width: Int, height: Int, mineCount: Int): GameMap {
        val field = List(height) { y -> MutableList<Tile>(width) { x -> Unopened(Point.from(x, y)) } }
        createMines(width, height, mineCount).forEach {
            val x = it.point.x
            val y = it.point.y
            field[y.value][x.value] = it
        }
        return GameMap(field)
    }

    private fun createMines(width: Int, height: Int, count: Int): List<Mine> {
        return RandomGenerator.points(width, height, count).map { Mine(it) }
    }
}
