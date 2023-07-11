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
            proceedSquareType(it)
        }
        println()
    }

    private fun proceedSquareType(gameBoardSquare: GameBoardSquare) {
        if (gameBoardSquare.isMine()) {
            print('*')
            return
        }
        if (gameBoardSquare is GameBoardSquare.NumberSquare) {
            print(gameBoardSquare.numOfNearByMine)
            return
        }
        throw IllegalArgumentException("잘못된 타입이 입력되었습니다.")
    }
}
