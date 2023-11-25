interface MineGenerator {
    fun generatePoint(height: Int, width: Int, count: Int): List<Point>
    fun generate(height: Int, width: Int, count: Int): Mines
}
