package minesweeper.ui

import minesweeper.BoardDrawing
import minesweeper.CellType

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

    private fun drawRow(row: List<CellType>) {
        row.forEach { cell ->
            val icon = CellIcon.valueOf(cell.name).icon
            print(icon)
        }
    }

    private fun nextLine() {
        println()
    }
}

enum class CellIcon(val icon: String) {
    MINE("*"),
    EMPTY("C"),
}
