package minesweeper.view

import minesweeper.model.GameResult

object ConsoleResultView : ResultView {
    private const val ROW_SEPARATOR = "\n"
    private const val CELL_SEPARATOR = " "

    override fun printMinesweeperGameStartMessage() {
        println("\n지뢰찾기 게임 시작")
    }

    override fun printMineBoard(gameResult: GameResult) {
        val boardView = gameResult.boardRows
            .joinToString(ROW_SEPARATOR) { it.joinToString(CELL_SEPARATOR) }

        println(boardView)
        println()
    }

    override fun printMinesweeperGameResult(gameResult: GameResult) {
        if (gameResult.lost) {
            println("Lost Game.")
            return
        }

        if (gameResult.win) {
            println("Win Game.")
        }
    }
}
