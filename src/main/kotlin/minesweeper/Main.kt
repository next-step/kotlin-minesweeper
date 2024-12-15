package minesweeper

import minesweeper.domain.DefaultLandmineFieldArchitect
import minesweeper.domain.GameBoard
import minesweeper.domain.LandmineFieldArchitect
import minesweeper.ui.ConsoleInput.inputColumnLength
import minesweeper.ui.ConsoleInput.inputCountOfLandmines
import minesweeper.ui.ConsoleInput.inputRowLength
import minesweeper.ui.ConsoleOutput.announceGameStarted
import minesweeper.ui.ConsoleOutput.displayCurrentGameBoard

fun main() {
    val rowLength = inputRowLength()

    val columnLength = inputColumnLength()

    val countOfLandmines = inputCountOfLandmines()

    val initialBoard = GameBoard.of(rowLength = rowLength, columnLength = columnLength)
    val landmineFieldArchitect: LandmineFieldArchitect = DefaultLandmineFieldArchitect()
    val gameBoard = landmineFieldArchitect.design(initialBoard, countOfLandmines)

    announceGameStarted()
    displayCurrentGameBoard(gameBoard.rows())
}
