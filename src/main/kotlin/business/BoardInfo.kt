package business

class BoardInfo(val height: Int, val width: Int, val mineCount: Int) {
    fun getAllPoints(): List<Point> = sequence {
        for (x in START_INDEX until height) for (y in START_INDEX until width) yield(Point(x, y))
    }.toList()

    companion object {
        private const val START_INDEX = 0
    }
}
