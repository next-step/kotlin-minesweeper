package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.domain.BoardMetadata
import minesweeper.domain.Coordinate
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
    val openedCoordinate = mutableSetOf<Coordinate>()
    while (board.isAllOpened().not()) {
        val openCoordinate = ConsoleInput.inputOpenCoordinate()
        val isOpened = board.canOpen(openCoordinate)
        if (isOpened.not()) {
            ConsoleOutput.loseGame()
            return
        }

        openedCoordinate.addAll(board.open(openCoordinate, countingBoard))
        ConsoleOutput.printOpenedBoard(boardHeight, boardWidth, openedCoordinate.toSet(), countingBoard)
    }
    ConsoleOutput.winGame(boardHeight, boardWidth, board, countingBoard)
}
