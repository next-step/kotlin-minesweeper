package minesweeper.ui

import minesweeper.domain.CountOfLandmines
import minesweeper.domain.cell.Location
import minesweeper.domain.service.GameBoardCellOpener
import minesweeper.domain.service.GameBoardCreator
import minesweeper.domain.service.GameState
import minesweeper.ui.ConsoleInput.inputCountOfLandmines
import minesweeper.ui.ConsoleInput.inputHeight
import minesweeper.ui.ConsoleInput.inputWidth
import minesweeper.ui.ConsoleOutput.announceGameStarted
import minesweeper.ui.ConsoleOutput.displayCurrentGameBoard

class MinesweeperController(
    private val gameBoardCreator: GameBoardCreator,
    private val gameBoardCellOpener: GameBoardCellOpener,
) {
    fun play() {
        val height = inputHeight()

        val width = inputWidth()

        val countOfLandmines = CountOfLandmines(inputCountOfLandmines())

        announceGameStarted()

        var gameBoard = gameBoardCreator.createBoard(height = height, width = width, countOfLandmines = countOfLandmines)

        while (GameState.from(gameBoard.currentState()) == GameState.CONTINUE) {
            displayCurrentGameBoard(gameBoard)
            println()

            print("open: ")
            val (row, column) = readln().split(", ").map { it.toInt() }
            val location = Location(row = row, column = column)

            gameBoard =
                runCatching {
                    gameBoardCellOpener.openGameBoardCell(gameBoard, location)
                }
                    .onFailure { e -> println(e.message) }
                    .getOrElse { gameBoard }
        }

        val gameState = GameState.from(gameBoard.currentState())

        if (gameState == GameState.LOSE) {
            println("Lose Game.")
        }
        if (gameState == GameState.WIN) {
            println("Win Game!!")
        }
    }
}
