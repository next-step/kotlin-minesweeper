package view

import domain.GameBoard
import enum.CellStatus

class OutputView {
    fun displayBoard(board: GameBoard) {
        board.forEachCell { position, status ->
            val symbol = when (status) {
                CellStatus.MINE -> '*'
                CellStatus.EMPTY -> 'C'
            }
            print("$symbol ")
            if (position.x == board.width - 1) {
                println()
            }
        }
    }
}
