package minesweeper.domain

object BoardGenerator {
    fun create(height: Int, width: Int, mineCount: Int): MineBoard {
        val board = createBoard(height, width)

        require(height * width > mineCount) { "지뢰의 개수가 보드판의 총 격자보다 큽니다." }

        placeMines(board, mineCount)
        return board
    }
    private fun createBoard(height: Int, width: Int): MineBoard {
        val boardInfo = (1..height).map { BoardRow((1..width).map { Cell(CellType.Empty) }) }
        return MineBoard(boardInfo)
    }

    private fun placeMines(board: MineBoard, mineCount: Int) {
        val height = board.getHeight
        val width = board.getWidth

        val randomPoints = mutableListOf<Point>()
        for (i in 0 until height) {
            for (j in 0 until width) {
                val point = Point(i, j)
                if (board.getCell(point).type == CellType.Empty) {
                    randomPoints.add(point)
                }
            }
        }
        randomPoints.shuffled().take(mineCount).forEach {
            board.getCell(it).updateCellType(CellType.Mine)
        }
    }
}
