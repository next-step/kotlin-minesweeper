package minesweeper.view

import minesweeper.domain.MineSweeper
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCell
import minesweeper.domain.cell.SafeCell

class ResultView {
    fun showMineSweeper(mineSweeper: MineSweeper) {
        (0 until mineSweeper.height).forEach {
            println(
                mineSweeper.getRow(it).joinToString(
                    " ",
                    transform = fun(cell: Cell): CharSequence {
                        if (!cell.isOpened) return "C"

                        return when (cell) {
                            is MineCell -> "*"
                            is SafeCell -> "${cell.countOfAdjacentMine}"
                        }
                    }
                )
            )
        }
    }

    fun showGameStart() {
        println("\n지뢰찾기 게임 시작")
    }

    fun showWinGame() {
        println("Win Game.")
    }

    fun showLoseGame() {
        println("Lose Game.")
    }
}
