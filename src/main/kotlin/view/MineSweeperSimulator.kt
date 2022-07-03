package view

import domain.Board
import domain.Dimension
import domain.Location.Companion.isMine
import domain.Location.Companion.open
import domain.Matrix
import view.OutputView.displayGameStart

fun main() {
    val width = InputView.getWidth()
    val height = InputView.getHeight()

    val dimension = Dimension(width = width, height = height)

    val numberOfMines = InputView.getMinesCount()

    val board = Board(Matrix(dimension, numberOfMines))

    displayGameStart()
    var continueGame = true
    while (continueGame) {
        val openLocation = InputView.getOpenLocation()
        val cell = board.cells.get(openLocation)!!
        cell.location.open()

        OutputView.showBoard(board)
        continueGame = !isMine(cell.location, board.cells)
    }

    if(!continueGame) {
        OutputView.displayGameEnd()
    }
}