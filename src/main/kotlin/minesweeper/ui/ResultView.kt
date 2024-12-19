package minesweeper.ui

import minesweeper.BoardDrawing
import minesweeper.DrawingCell
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
            val icon = when (cell) {
                is DrawingCell.MineCell -> CellIcon.MINE.icon
                is DrawingCell.NumberCell -> cell.mineCount.toString()
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
