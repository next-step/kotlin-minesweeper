package minesweeper.ui

import minesweeper.domain.GameBoardSquare

class ResultView {


    fun printGameBoard(gameBoard: List<List<GameBoardSquare>>) {
        println()
        println("지뢰찾기 게임 시작")
        gameBoard.forEach {
            printEachBoardLine(it)
        }
    }

    private fun printEachBoardLine(boardLine: List<GameBoardSquare>) {
        boardLine.forEach { print("${it.printValue()} ") }
        println()
    }
}
