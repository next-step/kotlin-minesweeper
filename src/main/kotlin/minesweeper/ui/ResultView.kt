package minesweeper.ui

import minesweeper.domain.Coordinate
import minesweeper.domain.Game
import minesweeper.domain.GameState
import minesweeper.domain.GameState.PROGRESS
import minesweeper.domain.GameState.TERMINATE
import minesweeper.domain.MineBoard

class ResultView {

    fun showStartGame(inputView: InputView, board: MineBoard) {
        println()
        println("지뢰찾기 게임 시작")

        val game = Game(board)

        var state: GameState
        do {
            val openPosition: String = inputView.inputOpen()
            state = game.open(openPosition)
            showBoard(board, state)
        } while (state == PROGRESS)
    }

    private fun showBoard(board: MineBoard, state: GameState) {
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
        if (coordinate.isMine()) return "* "
        if (coordinate.isOpen()) return coordinate.count.toString() + " "
        return "C "
    }
}
