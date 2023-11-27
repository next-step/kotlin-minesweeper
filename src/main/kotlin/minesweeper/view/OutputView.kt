package minesweeper.view

import minesweeper.model.board.Board
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Horizontal
import minesweeper.model.point.Vertical

object OutputView {
    fun printMineMap(board: Board) {
        println(renderingBoard(board))
    }

    fun renderingBoard(board: Board): String {
        return (0 until board.horizontalSize)
            .joinToString(separator = "\n") { verticalIndex -> renderingRow(board, Vertical(verticalIndex)) }
    }

    private fun renderingRow(board: Board, verticalIndex: Vertical): String {
        return (0 until board.verticalSize)
            .joinToString(separator = " ") { horizontalIndex -> renderingPoint(board, Coordinate(verticalIndex, Horizontal(horizontalIndex))) }
    }

    private fun renderingPoint(board: Board, coordinate: Coordinate): String {
        return board.points.symbol(coordinate)
    }
}
