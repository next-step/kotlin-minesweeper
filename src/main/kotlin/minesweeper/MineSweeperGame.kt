package minesweeper

import minesweeper.domain.GameBoard
import minesweeper.ui.ConsoleInput.inputColumnLength
import minesweeper.ui.ConsoleInput.inputCountOfLandmine
import minesweeper.ui.ConsoleInput.inputRowLength
import minesweeper.ui.ConsoleOutput.announceGameStarted
import minesweeper.ui.ConsoleOutput.displayCurrentGameBoard

fun main() {
    val rowLength = inputRowLength()

    val columnLength = inputColumnLength()

    val countOfLandmine = inputCountOfLandmine()

    val gameBoard = GameBoard.of(rowLength = rowLength, columnLength = columnLength).plantMines(countOfLandmine)

    announceGameStarted()
    displayCurrentGameBoard(gameBoard.rows())
}
