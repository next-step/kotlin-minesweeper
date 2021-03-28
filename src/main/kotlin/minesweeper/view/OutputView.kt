package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.NaturalNumber

internal class OutputView {
    fun render(board: Board) {
        print("\n지뢰찾기 게임 시작")

        var curY = NaturalNumber.ZERO
        println()

        board.cells.forEach {
            if (it.key.y != curY) {
                curY = it.key.y
                println()
            }

            print("${it.value.display} ")
        }
    }
}
