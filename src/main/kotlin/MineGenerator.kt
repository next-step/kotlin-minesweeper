fun interface MineGenerator {
    fun generate(height: Int, width: Int, count: Int): List<Point>
}
