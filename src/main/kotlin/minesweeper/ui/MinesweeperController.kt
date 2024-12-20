package minesweeper.ui

import minesweeper.domain.CountOfLandmines
import minesweeper.domain.GameBoard
import minesweeper.domain.cell.Location
import minesweeper.domain.service.GameBoardCellOpener
import minesweeper.domain.service.GameBoardCreator
import minesweeper.domain.service.GameState
import minesweeper.ui.ConsoleInput.inputCountOfLandmines
import minesweeper.ui.ConsoleInput.inputHeight
import minesweeper.ui.ConsoleInput.inputSelectLocation
import minesweeper.ui.ConsoleInput.inputWidth
import minesweeper.ui.ConsoleOutput.announceGameLose
import minesweeper.ui.ConsoleOutput.announceGameStarted
import minesweeper.ui.ConsoleOutput.announceGameWin
import minesweeper.ui.ConsoleOutput.displayCurrentGameBoard
import minesweeper.ui.ConsoleOutput.printException

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

        var gameState = GameState.from(gameBoard.currentState())

        while (gameState == GameState.CONTINUE) {
            displayCurrentGameBoard(gameBoard)

            val location = inputSelectLocation()

            gameBoard = processOpenCell(gameBoard, location)

            gameState = GameState.from(gameBoard.currentState())
        }

        when (gameState) {
            GameState.WIN -> announceGameWin()
            GameState.LOSE -> announceGameLose()
            else -> {}
        }
    }

    private fun processOpenCell(
        gameBoard: GameBoard,
        location: Location,
    ): GameBoard {
        return runCatching { gameBoardCellOpener.openGameBoardCell(gameBoard, location) }
            .onFailure { printException(it) }
            .getOrElse { gameBoard }
    }
}
