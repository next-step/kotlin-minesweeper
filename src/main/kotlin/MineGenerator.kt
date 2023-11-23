object MineGenerator {
    fun generate(height: Int, width: Int, count: Int): List<Point> {
        val total = height * width
        return (0 until total).shuffled().take(count).map { Point(it / width, it % width) }
    }
}

data class Point(val x: Int, val y: Int)
