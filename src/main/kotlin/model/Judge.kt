package model

class Judge(board: Board, private val width: Int, private val height: Int) {
    val squares = board.squares

    fun openSquare(row: Int, col: Int) {
        if (row < 0 || row >= width || col < 0 || col >= height || squares[row][col].isOpen || squares[row][col].type == SquareType.MINE) {
            return
        }

        squares[row][col] = squares[row][col].open()

        if (squares[row][col].mineCount == 0) {
            (-1..1).flatMap { dx ->
                (-1..1).map { dy -> dx to dy }
            }.filter { (dx, dy) ->
                dx != 0 || dy != 0
            }.forEach { (dx, dy) ->
                openSquare(row + dx, col + dy)
            }
        }
    }

    fun clickSquare(x: Int, y: Int): SquareType {
        val row = y - 1
        val col = x - 1

        if (squares[row][col].type == SquareType.MINE) {
            squares[row][col] = squares[row][col].open()
            return SquareType.MINE
        }
        openSquare(row, col)
        return SquareType.TILE
    }
}
