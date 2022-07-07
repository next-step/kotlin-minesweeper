package domain

data class Point(val x: Int, val y: Int) {
    fun diagonalUpLeft(): Point = Point(x - 1, y - 1)

    fun up(): Point = Point(x, y - 1)

    fun diagonalUpRight(): Point = Point(x + 1, y - 1)

    fun left(): Point = Point(x - 1, y)

    fun right(): Point = Point(x + 1, y)

    fun diagonalBottomLeft(): Point = Point(x - 1, y + 1)

    fun bottom(): Point = Point(x, y + 1)

    fun diagonalBottomRight(): Point = Point(x + 1, y + 1)
}
