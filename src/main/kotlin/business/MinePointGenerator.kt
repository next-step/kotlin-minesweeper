package business

fun interface MinePointGenerator {
    fun generate(height: Int, width: Int, count: Int): List<Point>
}
