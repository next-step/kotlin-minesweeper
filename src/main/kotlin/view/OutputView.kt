package view

import domain.GameBoard

class OutputView {
    fun displayBoard(board: GameBoard) {
        board.forEachCell { position, status ->
            print("${status.symbol} ")
            if (position.x == board.width - 1) {
                println()
            }
        }
    }
}
