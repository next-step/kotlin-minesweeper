package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Point

object ResultView {
    fun resultBoard(board: Board, lastX: Int) = board.forEachPoints { draw(it, lastX) }

    private fun draw(point: Point, lastX: Int) {
        if (point.isLastX(lastX)) {
            println(drawWhat(point))
        } else {
            print(drawWhat(point))
            print(" ")
        }
    }

    private fun drawWhat(point: Point): String {
        if (point.isOpen) {
            return drawOpen(point)
        }
        return "C"
    }

    private fun drawOpen(point: Point): String {
        if (point.isMine()) {
            return "*"
        }
        return point.mineCount.toString()
    }
}
