package minesweeper

import minesweeper.domain.CountOfLandmines
import minesweeper.domain.service.GameBoardCreator
import minesweeper.ui.ConsoleInput.inputCountOfLandmines
import minesweeper.ui.ConsoleInput.inputHeight
import minesweeper.ui.ConsoleInput.inputWidth
import minesweeper.ui.ConsoleOutput.announceGameStarted
import minesweeper.ui.ConsoleOutput.displayCurrentGameBoard

fun main() {
    val height = inputHeight()

    val width = inputWidth()

    val countOfLandmines = CountOfLandmines(inputCountOfLandmines())

    val gameBoardCreator = GameBoardCreator()
    val gameBoard = gameBoardCreator.design(height = height, width = width, countOfLandmines = countOfLandmines)
    val allOpenedGameBoard = gameBoard.openAll()

    announceGameStarted()
    displayCurrentGameBoard(allOpenedGameBoard)
}
