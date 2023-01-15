package minesweeper.ui

import minesweeper.domain.Coordinate
import minesweeper.domain.GameState
import minesweeper.domain.GameState.TERMINATE
import minesweeper.domain.MineBoard

class ResultView {

    fun printStartGame() {
        println()
        println("지뢰찾기 게임 시작")
    }

    fun showBoard(board: MineBoard, state: GameState) {
        if (state == TERMINATE) {
            println("Lose Game.")
            return
        }
        print(makeBoardText(board))
        println()
    }

    private fun makeBoardText(board: MineBoard): String {
        var result = ""
        repeat(board.rowSize()) { rowIndex ->
            result += getColumnText(board, rowIndex) + "\n"
        }
        return result
    }

    private fun getColumnText(board: MineBoard, rowIndex: Int): String {
        var result = ""
        val level = rowIndex * board.columnSize()

        repeat(board.columnSize()) { columnIndex ->
            val coordinate = board.coordinates[level + columnIndex]
            result += getCoordinateText(coordinate)
        }

        return result
    }

    private fun getCoordinateText(coordinate: Coordinate): String {
        if (coordinate.isOpen()) return coordinate.count.toString() + " "
        return "C "
    }
}
