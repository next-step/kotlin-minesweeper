package minesweeper.ui

import minesweeper.BoardDrawing
import minesweeper.DrawingRow

class ResultView {
    fun drawBoard(draw: BoardDrawing) {
        while (draw.hasNext()) {
            drawRow(draw.next())
            nextLine()
        }
    }

    fun startView() {
        println("지뢰찾기 게임 시작")
    }

    private fun drawRow(row: DrawingRow) {
        row.forEach { cell ->
            val icon =
                if (cell.isMine) {
                    CellIcon.MINE.icon
                } else {
                    cell.neighborsMineCount.toString()
                }
            print(icon)
        }
    }

    private fun nextLine() {
        println()
    }
}

enum class CellIcon(val icon: String) {
    MINE("*"),
}
