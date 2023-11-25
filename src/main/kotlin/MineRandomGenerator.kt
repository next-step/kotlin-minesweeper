class MineRandomGenerator : MineGenerator {
    override fun generatePoint(height: Int, width: Int, count: Int): List<Point> {
        val total = height * width
        return (Const.START_INDEX until total).shuffled().take(count).map { Point(it / width, it % width) }
    }

    override fun generate(height: Int, width: Int, count: Int): Mines {
        return Mines(generatePoint(height, width, count).map { Mine(it) })
    }
}
