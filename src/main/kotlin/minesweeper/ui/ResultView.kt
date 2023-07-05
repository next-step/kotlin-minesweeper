package minesweeper.ui

class ResultView {

    fun printGameBoard(gameBoard: Array<Array<Char>>) {
        println()
        println("지뢰찾기 게임 시작")
        gameBoard.forEach {
            printEachBoardLine(it)
        }
    }

    private fun printEachBoardLine(boardLine: Array<Char>) {
        boardLine.forEach { print("${it} ") }
        println()
    }
}