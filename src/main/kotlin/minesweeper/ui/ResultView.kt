package minesweeper.ui

import minesweeper.domain.GameBoardSquare

class ResultView {

    fun printGetHeight() {
        println("높이를 입력하세요.")
    }

    fun printGetWidth() {
        println()
        println("너비를 입력하세요.")
    }

    fun printGetMinesNumber() {
        println()
        println("지뢰는 몇 개인가요?")
    }

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
