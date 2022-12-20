package minesweeper.ui

import minesweeper.domain.Coordinate
import minesweeper.domain.MineBoard

class ResultView {

    fun showBoard(board: MineBoard) {
        println()
        println("지뢰찾기 게임 시작")
        print(makeBoardText(board))
    }

    private fun makeBoardText(board: MineBoard): String {
        var result = ""
        repeat(board.rowSize) { rowIndex ->
            result += getColumnText(board, rowIndex) + "\n"
        }
        return result
    }

    private fun getColumnText(board: MineBoard, rowIndex: Int): String {
        var result = ""
        val level = rowIndex * board.columnSize

        repeat(board.columnSize) { columnIndex ->
            val coordinate = board.coordinates[level + columnIndex]
            result += getCoordinateText(coordinate)
        }

        return result
    }

    private fun getCoordinateText(coordinate: Coordinate): String {
        if (coordinate.isMine()) return "* "
        return "C "
    }
}
