package domain

import error.ErrorMessage

class Point(val row: Int, val col: Int) {
    fun isValid(rows: Int, cols: Int): Boolean {
        return row in 0 until rows && col in 0 until cols
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

    companion object {
        fun parsePoint(inputPoint: String): Point {
            return runCatching {
                val (row, col) = inputPoint.split(",").map { it.trim() }
                Point(row.toInt(), col.toInt())
            }.getOrElse {
                throw IllegalArgumentException(ErrorMessage.WRONG_INPUT_MESSAGE.message)
            }
        }
    }
}
