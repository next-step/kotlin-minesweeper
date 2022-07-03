import domain.Point

class AroundMineChecker(board: Map<Point, Square>, private val currentPoint: Point) {

    private val _board: MutableMap<Point, Square> = board.toMutableMap()

    fun getBoard(): Map<Point, Square> {
        checkLeft(currentPoint)
        checkRight(currentPoint)
        return _board.toMap()
    }

    private fun checkLeft(currentPoint: Point) {
        if (!_board.containsKey(currentPoint)) return
        if (_board.getValue(currentPoint).isMine()) return
        else {
            val countMine = AroundMine(_board, currentPoint).countMine()
            _board.replace(currentPoint, NonMine(countMine))

            checkLeft(diagonalUpLeft(currentPoint))
            checkLeft(left(currentPoint))
            checkLeft(diagonalBottomLeft(currentPoint))
            checkLeft(bottom(currentPoint))
        }
    }

    private fun checkRight(currentPoint: Point) {
        if (!_board.containsKey(currentPoint)) return
        if (_board.getValue(currentPoint).isMine()) return
        else {
            val countMine = AroundMine(_board, currentPoint).countMine()
            _board.replace(currentPoint, NonMine(countMine))
            checkRight(up(currentPoint))
            checkRight(diagonalUpRight(currentPoint))
            checkRight(diagonalBottomRight(currentPoint))
            checkRight(right(currentPoint))
        }
    }

    private fun diagonalUpLeft(currentPoint: Point): Point = Point(currentPoint.x - 1, currentPoint.y - 1)

    private fun up(currentPoint: Point): Point = Point(currentPoint.x, currentPoint.y - 1)

    private fun diagonalUpRight(currentPoint: Point): Point = Point(currentPoint.x + 1, currentPoint.y - 1)

    private fun left(currentPoint: Point): Point = Point(currentPoint.x - 1, currentPoint.y)

    private fun right(currentPoint: Point): Point = Point(currentPoint.x + 1, currentPoint.y)

    private fun diagonalBottomLeft(currentPoint: Point): Point = Point(currentPoint.x - 1, currentPoint.y + 1)

    private fun bottom(currentPoint: Point): Point = Point(currentPoint.x, currentPoint.y + 1)

    private fun diagonalBottomRight(currentPoint: Point): Point = Point(currentPoint.x + 1, currentPoint.y + 1)
}
