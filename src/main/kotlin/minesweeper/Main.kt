package minesweeper

import minesweeper.domain.DefaultLandmineFieldArchitect
import minesweeper.domain.GameBoard
import minesweeper.domain.LandmineFieldArchitect
import minesweeper.ui.ConsoleInput.inputCountOfLandmines
import minesweeper.ui.ConsoleInput.inputHeight
import minesweeper.ui.ConsoleInput.inputWidth
import minesweeper.ui.ConsoleOutput.announceGameStarted
import minesweeper.ui.ConsoleOutput.displayCurrentGameBoard

fun main() {
    val height = inputHeight()

    val width = inputWidth()

    val countOfLandmines = inputCountOfLandmines()

    val initialBoard = GameBoard.of(height = height, width = width)
    val landmineFieldArchitect: LandmineFieldArchitect = DefaultLandmineFieldArchitect()
    val gameBoard = landmineFieldArchitect.design(initialBoard, countOfLandmines)

    announceGameStarted()
    displayCurrentGameBoard(gameBoard.rows())
}
