package minesweeper.view

import minesweeper.dto.MineBoardDto
import minesweeper.model.GameStatus

object ConsoleResultView : ResultView {
    private const val ROW_SEPARATOR = "\n"
    private const val CELL_SEPARATOR = " "

    override fun printMinesweeperGameStartMessage() {
        println("\n지뢰찾기 게임 시작")
    }

    override fun printMineBoard(gameResult: MineBoardDto) {
        val boardView = gameResult.boardRows
            .joinToString(ROW_SEPARATOR) { it.boardRow.joinToString(CELL_SEPARATOR) }

        println(boardView)
        println()
    }

    override fun printMinesweeperGameStatus(gameStatus: GameStatus) {
        if (gameStatus.lost) {
            println("Lost Game.")
            return
        }

        if (gameStatus.win) {
            println("Win Game.")
        }
    }
}
