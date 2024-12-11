package ui

import GameBoard
import ui.ConsoleInput.inputColumnLength
import ui.ConsoleInput.inputCountOfLandmine
import ui.ConsoleInput.inputRowLength
import ui.ConsoleOutput.announceGameStarted
import ui.ConsoleOutput.displayCurrentGameBoard

fun main() {
    val rowLength = inputRowLength()

    val columnLength = inputColumnLength()

    val countOfLandmine = inputCountOfLandmine()

    val gameBoard = GameBoard.of(rowLength = rowLength, columnLength = columnLength).plantMines(countOfLandmine)

    announceGameStarted()
    displayCurrentGameBoard(gameBoard.rows())
}
