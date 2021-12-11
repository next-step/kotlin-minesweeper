package minesweeper.view

import minesweeper.domain.board.BoardResult

object OutputView {

    fun printStart(board: BoardDto) {
        println("지뢰찾기 게임 시작")
        printBoard(board)
    }

    fun printBoard(board: BoardDto) {
        board.rows.forEach {
            printRow(it)
        }
    }

    fun printResult(result: BoardResult) {
        when(result) {
            is BoardResult.Win -> println("Win Game.")
            is BoardResult.Lose -> println("Lose Game.")
            is BoardResult.Stopped -> println("Player stopped game.")
        }
    }

    private fun printRow(row: RowDto) {
        row.cells.forEach {
            print("${it.toView()} ")
        }
        println()
    }

    private fun CellDto.toView(): String {
        return when {
            !isOpen -> "C"
            mine -> "*"
            else -> aroundMineCount.toString()
        }
    }
}
