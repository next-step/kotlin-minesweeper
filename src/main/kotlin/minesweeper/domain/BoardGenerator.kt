package minesweeper.domain

import minesweeper.view.BoardStatus

object BoardGenerator {
    fun createBoard(boardStatus: BoardStatus): MineBoard {
        val height = boardStatus.height
        val width = boardStatus.width
        val mineCount = boardStatus.mineCount

        require(height * width > mineCount) { "지뢰의 개수가 보드판의 총 격자보다 큽니다." }

        val allPoints = getAllPoints(height, width)
        val minePointsSet = allPoints.shuffled().take(mineCount).toSet()
        val minePoints = MinePoints(minePointsSet)

        val boardInfo = (0 until height).map { row ->
            BoardRow(
                (0 until width).map { col ->
                    val point = Point(row, col)
                    if (minePoints.contains(point)) {
                        MineCell(point)
                    } else {
                        EmptyCell(point, minePoints.countNeighborMines(point))
                    }
                }
            )
        }
        return MineBoard(boardInfo)
    }

    private fun getAllPoints(height: Int, width: Int): List<Point> {
        return List(height * width) {
            Point(it / width, it % width)
        }.toList()
    }
}
