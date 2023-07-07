package minesweeper.ui

import minesweeper.domain.GameBoardSquare

object ResultView {
    fun printGameBoard(gameBoard: List<List<GameBoardSquare>>) {
        println()
        println("지뢰찾기 게임 시작")
        gameBoard.forEach {
            printEachBoardLine(it)
        }
    }

    private fun printEachBoardLine(boardLine: List<GameBoardSquare>) {
        boardLine.forEach {
            val proceedSquareType = proceedSquareType(it.isMine())
            print(proceedSquareType)
        }
        println()
    }

    private fun proceedSquareType(isMine: Boolean): Char {
        if (isMine) {
            return '*'
        }
        return 'C'
    }
}
