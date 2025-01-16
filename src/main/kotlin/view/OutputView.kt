package view

import model.Square
import model.SquareType

object OutputView {
    private const val UI_TILE = "C"
    private const val UI_MINE = "*"


    fun showBoard(board: List<Square>, width: Int) {
        board.forEachIndexed { index, square ->
            if (square.isOpen) {
                if (square.type == SquareType.TILE) {
                    print("${square.mineCount} ")
                } else if (square.type == SquareType.MINE) {
                    print("$UI_MINE ")
                }
            } else {
                print("$UI_TILE ")
            }
            adjustWidth(index, width)
        }
    }

    fun showLoseGame(selectedSquareType: SquareType): Boolean {
        if (selectedSquareType == SquareType.MINE) {
            println("Lose Game.")
            return false
        }
        return true
    }

    private fun adjustWidth(index: Int, width: Int) {
        if ((index + 1) % width == 0) {
            println()
        }
    }
}
