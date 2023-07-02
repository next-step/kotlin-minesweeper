package minesweeper

import minesweeper.domain.MineBoard.Companion.generateNewMineBoard
import minesweeper.view.inputHeight
import minesweeper.view.inputMineCount
import minesweeper.view.inputWidth
import minesweeper.view.printMineBoardView

fun main() {
    val mineBoard = generateNewMineBoard(height = inputHeight(), width = inputWidth())
    mineBoard.placeMine(mineCount = inputMineCount())
    printMineBoardView(mineBoard.currentBoard())
}
