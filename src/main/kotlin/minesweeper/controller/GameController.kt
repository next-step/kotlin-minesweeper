package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.domain.BoardMetadata
import minesweeper.domain.CountingBoard
import minesweeper.domain.rule.RandomMineGenerationRule
import minesweeper.view.ConsoleInput
import minesweeper.view.ConsoleOutput

fun main() {
    val boardHeight = ConsoleInput.inputBoardHeight()
    val boardWidth = ConsoleInput.inputBoardWidth()
    val numOfMine = ConsoleInput.inputNumOfMine()

    val board = Board(BoardMetadata(boardHeight, boardWidth, numOfMine), RandomMineGenerationRule())
    val countingBoard = CountingBoard(board)

    ConsoleOutput.startGame()
    while (board.isAllOpened().not()) {
        val openCoordinate = ConsoleInput.inputOpenCoordinate()
        val canOpen = board.canOpen(openCoordinate)
        if (canOpen.not()) {
            ConsoleOutput.loseGame()
            return
        }

        board.open(openCoordinate, countingBoard)
        ConsoleOutput.printOpenedBoard(boardHeight, boardWidth, board.openedCoordinates(), countingBoard)
    }
    ConsoleOutput.winGame(boardHeight, boardWidth, board, countingBoard)
}
