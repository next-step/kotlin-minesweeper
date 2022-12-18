package minesweeper.controller

import minesweeper.domain.Coordinate
import minesweeper.domain.MineBoard

object Controller {

    fun makeBoardText(board: MineBoard): String {
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
        return if (coordinate.isMine()) "* " else "C "
    }
}
