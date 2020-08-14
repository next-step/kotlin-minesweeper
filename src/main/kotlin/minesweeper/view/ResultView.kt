package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Point

object ResultView {
    fun resultBoard(board: Board, lastX: Int) {
        board.points.forEach { draw(it, lastX) }
    }

    private fun draw(point: Point, lastX: Int) {
        if (point.coordinate.x == lastX) {
            println(drawWhat(point))
        } else {
            print(drawWhat(point))
            print(" ")
        }
    }

    private fun drawWhat(point: Point): String {
        if (point.isMine) {
            return "*"
        }
        return "C"
    }
}
