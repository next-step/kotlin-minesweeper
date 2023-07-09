package minesweeper.domain

import minesweeper.view.BoardStatus

object BoardGenerator {
    fun create(boardStatus: BoardStatus): MineBoard {
        val height = boardStatus.height
        val width = boardStatus.width
        val mineCount = boardStatus.mineCount

        val board = createBoard(height, width)

        require(height * width > mineCount) { "지뢰의 개수가 보드판의 총 격자보다 큽니다." }

        placeMines(board, mineCount)
        MineCounter.calculateNeighborMines(board)
        return board
    }

    private fun createBoard(height: Int, width: Int): MineBoard {
        val boardInfo = (1..height).map { BoardRow((1..width).map { EmptyCell(Point(height, width)) }) }
        return MineBoard(boardInfo)
    }

    private fun placeMines(board: MineBoard, mineCount: Int) {
        val height = board.height
        val width = board.width

        val randomPoints = saveAllPoints(height, width)

        randomPoints.shuffled().take(mineCount).forEach {
            board.updateToMine(it)
        }
    }

    private fun saveAllPoints(height: Int, width: Int): MutableList<Point> {
        val allPoints = mutableListOf<Point>()
        for (i in 0 until height) {
            for (j in 0 until width) {
                val point = Point(i, j)
                allPoints.add(point)
            }
        }
        return allPoints
    }
}
