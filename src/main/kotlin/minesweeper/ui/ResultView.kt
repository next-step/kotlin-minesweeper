package minesweeper.ui

class ResultView {

    fun printGameBoard(gameBoard: List<List<Char>>) {
        println()
        println("지뢰찾기 게임 시작")
        gameBoard.forEach {
            printEachBoardLine(it)
        }
    }

    private fun printEachBoardLine(boardLine: List<Char>) {
        boardLine.forEach { print("$it ") }
        println()
    }
}
