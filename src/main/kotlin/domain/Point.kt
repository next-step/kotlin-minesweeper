package domain

class Point(val row: Int, val col: Int) {
    fun isValid(boardSettings: BoardSettings): Boolean {
        return row in 0 until boardSettings.row && col in 0 until boardSettings.col
    }

    fun getNeighborPoints(): List<Point> {
        return listOf(
            Point(row - 1, col - 1),
            Point(row - 1, col),
            Point(row - 1, col + 1),
            Point(row, col - 1),
            Point(row, col + 1),
            Point(row + 1, col - 1),
            Point(row + 1, col),
            Point(row + 1, col + 1),
        )
    }
}

