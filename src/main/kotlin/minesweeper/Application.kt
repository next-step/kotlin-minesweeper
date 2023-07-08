package minesweeper

import minesweeper.domain.MineBoard
import minesweeper.domain.MineBoard.Companion.generateNewMineBoard
import minesweeper.view.inputCoordinate
import minesweeper.view.inputHeight
import minesweeper.view.inputMineCount
import minesweeper.view.inputWidth
import minesweeper.view.printMineBoardView

fun main() {
    val mineBoard = generateNewMineBoard(height = inputHeight(), width = inputWidth())
    mineBoard.placeMine(mineCount = inputMineCount())
    println("지뢰찾기 게임 시작")
    runMineGame(mineBoard)
}

private fun runMineGame(mineBoard: MineBoard) {
    if (mineBoard.isEndGame()) {
        println("게임 종료")
        return
    }
    mineBoard.open(inputCoordinate().toCoordinate())
    printMineBoardView(mineBoard.currentBoard())
    runMineGame(mineBoard)
}
