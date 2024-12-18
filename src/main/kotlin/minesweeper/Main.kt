package minesweeper

import minesweeper.domain.CountOfLandmines
import minesweeper.domain.GameBoard
import minesweeper.domain.strategy.DefaultLandmineFieldArchitect
import minesweeper.domain.strategy.LandmineFieldArchitect
import minesweeper.ui.ConsoleInput.inputCountOfLandmines
import minesweeper.ui.ConsoleInput.inputHeight
import minesweeper.ui.ConsoleInput.inputWidth
import minesweeper.ui.ConsoleOutput.announceGameStarted
import minesweeper.ui.ConsoleOutput.displayCurrentGameBoard

fun main() {
    val height = inputHeight()

    val width = inputWidth()

    val countOfLandmines = CountOfLandmines(inputCountOfLandmines())

    val initialBoard = GameBoard.of(height = height, width = width)
    val landmineFieldArchitect: LandmineFieldArchitect = DefaultLandmineFieldArchitect()
    val gameBoard = landmineFieldArchitect.design(initialBoard, countOfLandmines)

    announceGameStarted()
    displayCurrentGameBoard(gameBoard.rows)
}
