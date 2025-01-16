package model

class Board(val squares: Array<Array<Square>>) {
    fun updateSquare(row: Int, col: Int, square: Square): Board {
        squares[row][col] = square
        return Board(squares)
    }

    companion object {
        fun createBoard(boardHeight: BoardHeight, boardWidth: BoardWidth, mines: List<Int>): Board {
            val squares = Array(boardHeight.height) { Array(boardWidth.width) { Square(SquareType.TILE) } }
            var board = Board(squares)

            mines.forEach { index ->
                val row = index / boardWidth.width
                val col = index % boardWidth.width
                board = board.updateSquare(row, col, Square(SquareType.MINE))
            }

            val updatedSquares = MineCounter().calculateMineCounts(boardHeight.height, boardWidth.width, board.squares.flatten())
            updatedSquares.forEachIndexed { index, square ->
                val row = index / boardWidth.width
                val col = index % boardWidth.width
                board = board.updateSquare(row, col, square)
            }

            return board
        }
    }
}
