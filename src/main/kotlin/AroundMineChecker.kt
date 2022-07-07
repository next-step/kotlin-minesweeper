import domain.Point

class AroundMineChecker(private val mineBoard: Map<Point, Square>, private val currentPoint: Point) {

    fun isFinish(): Boolean = mineBoard.getValue(currentPoint).isMine()

    fun getBoard(): Map<Point, Square> {
        val checkLeft = checkLeft(currentPoint, mineBoard)
        return checkRight(currentPoint, checkLeft)
    }

    private fun checkLeft(currentPoint: Point, acc: Map<Point, Square> = emptyMap()): Map<Point, Square> {
        val board = acc.toMutableMap()

        if (!board.containsKey(currentPoint)) return acc
        if (board.getValue(currentPoint).isMine()) return acc

        val countMine = AroundMine(board, currentPoint).countMine()
        board.replace(currentPoint, NonMine(countMine))

        val diagonalUpLeft = checkLeft(currentPoint.diagonalUpLeft(), board)
        val left = checkLeft(currentPoint.left(), diagonalUpLeft)
        val diagonalBottomLeft = checkLeft(currentPoint.diagonalBottomLeft(), left)
        return checkLeft(currentPoint.bottom(), diagonalBottomLeft)
    }

    private fun checkRight(currentPoint: Point, acc: Map<Point, Square> = emptyMap()): Map<Point, Square> {
        val board = acc.toMutableMap()

        if (!board.containsKey(currentPoint)) return acc
        if (board.getValue(currentPoint).isMine()) return acc

        val countMine = AroundMine(board, currentPoint).countMine()
        board.replace(currentPoint, NonMine(countMine))
        val up = checkRight(currentPoint.up(), board)
        val diagonalUpRight = checkRight(currentPoint.diagonalUpRight(), up)
        val diagonalBottomRight = checkRight(currentPoint.diagonalBottomRight(), diagonalUpRight)
        return checkRight(currentPoint.right(), diagonalBottomRight)
    }

}
