import domain.Point

class AroundMine(private val board: Map<Point, Square>, private val currentPoint: Point) {

    fun countMine() = listOf<Boolean>()
        .asSequence()
        .plus(diagonalUpLeft())
        .plus(up())
        .plus(diagonalUpRight())
        .plus(left())
        .plus(right())
        .plus(diagonalBottomLeft())
        .plus(bottom())
        .plus(diagonalBottomRight())
        .count { it }

    private fun diagonalUpLeft(): Boolean = isMine(Point(currentPoint.x - 1, currentPoint.y - 1))

    private fun up(): Boolean = isMine(Point(currentPoint.x, currentPoint.y - 1))

    private fun diagonalUpRight(): Boolean = isMine(Point(currentPoint.x + 1, currentPoint.y - 1))

    private fun left(): Boolean = isMine(Point(currentPoint.x - 1, currentPoint.y))

    private fun right(): Boolean = isMine(Point(currentPoint.x + 1, currentPoint.y))

    private fun diagonalBottomLeft(): Boolean = isMine(Point(currentPoint.x - 1, currentPoint.y + 1))

    private fun bottom(): Boolean = isMine(Point(currentPoint.x, currentPoint.y + 1))

    private fun diagonalBottomRight(): Boolean = isMine(Point(currentPoint.x + 1, currentPoint.y + 1))

    private fun isMine(targetPoint: Point): Boolean {
        if (containPoint(targetPoint)) {
            val value = board.getValue(targetPoint)
            if (value.isMine()) return true
        }
        return false
    }

    private fun containPoint(point: Point): Boolean = board.containsKey(point)


}
