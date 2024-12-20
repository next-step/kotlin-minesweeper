package minesweeper.ui

import minesweeper.BoardDrawing
import minesweeper.DrawingCell
import minesweeper.DrawingRow
import minesweeper.Playing
import minesweeper.State

class ResultView {
    fun startView() {
        println("지뢰찾기 게임 시작")
    }

    fun drawBoard(draw: State) {
        if (draw is Playing) {
            drawBoard(draw.displayBoard())
            return
        }
        resultDraw(draw.isWin())
    }

    private fun drawRow(row: DrawingRow) {
        row.forEach { cell ->
            val icon =
                when (cell) {
                    is DrawingCell.MineCell -> CellIcon.MINE.icon
                    is DrawingCell.CloseCell -> CellIcon.CLOSE.icon
                    is DrawingCell.OpenCell -> cell.cellValue.toString()
                }
            print(icon)
        }
    }

    private fun drawBoard(draw: BoardDrawing) {
        while (draw.hasNext()) {
            drawRow(draw.next())
            nextLine()
        }
    }

    private fun resultDraw(win: Boolean) {
        if (win) {
            println("Win Game.")
        } else {
            println("Lose Game.")
        }
    }

    private fun nextLine() {
        println()
    }
}

enum class CellIcon(val icon: String?) {
    CLOSE("C"),
    MINE("*"),
}
