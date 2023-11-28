package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.domain.BoardMetadata
import minesweeper.domain.rule.RandomMineGenerationRule
import minesweeper.view.ConsoleInput
import minesweeper.view.ConsoleOutput

fun main() {
    val boardHeight = ConsoleInput.inputBoardHeight()
    val boardWidth = ConsoleInput.inputBoardWidth()
    val numOfMine = ConsoleInput.inputNumOfMine()

    val board = Board(BoardMetadata(boardHeight, boardWidth, numOfMine), RandomMineGenerationRule())

    ConsoleOutput.printBoard(boardHeight, boardWidth, board)
}
