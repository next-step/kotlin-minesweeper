package minesweeper.view

import minesweeper.model.MineBoardResult

object ConsoleResultView : ResultView {
    private const val ROW_SEPARATOR = "\n"
    private const val CELL_SEPARATOR = " "

    override fun printMineBoard(boardResult: MineBoardResult) {
        println("\n지뢰찾기 게임 시작")

        val boardView = boardResult.boardRows
            .joinToString(ROW_SEPARATOR) { it.joinToString(CELL_SEPARATOR) }

        println(boardView)
    }
}
