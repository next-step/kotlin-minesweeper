package minesweeper.view

import minesweeper.model.board.Board
import minesweeper.model.point.Coordinate
import minesweeper.model.point.Horizontal
import minesweeper.model.point.Vertical
import minesweeper.view.render.MineRenderingStrategy
import minesweeper.view.render.impl.AttributeRenderingStrategy

class OutputView(
    private val renderingStrategy: MineRenderingStrategy = AttributeRenderingStrategy,
) {
    fun printMineMap(board: Board) {
        println(renderingBoard(board))
    }

    fun renderingBoard(board: Board): String {
        return (0 until board.limit.horizontalLimit.value)
            .joinToString(separator = "\n") { verticalIndex -> renderingRow(board, Vertical(verticalIndex)) }
    }

    private fun renderingRow(board: Board, verticalIndex: Vertical): String {
        return (0 until board.limit.verticalLimit.value)
            .joinToString(separator = " ") { horizontalIndex -> renderingPoint(board, Coordinate(verticalIndex, Horizontal(horizontalIndex))) }
    }

    private fun renderingPoint(board: Board, coordinate: Coordinate): String {
        return renderingStrategy.symbolOf(board, coordinate)
    }

    fun gameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printGameResult() {
        println("Lose Game.")
    }
}
