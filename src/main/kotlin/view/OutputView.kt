package view

import domain.Board

object OutputView {

    fun printBoard(board: Board) {
        board
            .rows
            .forEach { row ->
                row
                    .cells
                    .forEach { cell ->
                        print("${cell.type.label} ")
                    }

                println()
            }
    }
}
