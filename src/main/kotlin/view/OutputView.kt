package view

import domain.Board

object OutputView {
    private const val MINE_SYMBOL = "* "
    private const val CELL_SYMBOL = "C "

    fun notifyGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        for (i in 0 until board.height) {
            for (j in 0 until board.width) {
                val foundCell = board.cells.first { it.row == i && it.column == j }
                if (foundCell.hasMine) {
                    print(MINE_SYMBOL)
                } else {
                    print(CELL_SYMBOL)
                }
            }
            println()
        }
    }
}
