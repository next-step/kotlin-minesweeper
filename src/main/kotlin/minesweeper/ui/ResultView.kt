package minesweeper.ui

import minesweeper.CellType

class ResultView {
    fun drawBoard(draw: List<List<CellType>>) {
        draw.forEach { row ->
            drawRow(row)
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