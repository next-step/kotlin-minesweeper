package minesweeper.view

import minesweeper.domain.MineSweeper
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.MineCell
import minesweeper.domain.cell.SafeCell

class ResultView {
    fun showMineSweeper(mineSweeper: MineSweeper) {
        println("지뢰찾기 게임 시작")
        println(mineSweeper.getHeight())
        (0 until mineSweeper.getHeight()).forEach {
            println(
                mineSweeper.getRow(it).joinToString(" ",
                    transform = fun(cell: Cell): CharSequence {
                        if (cell.isClosed) return "C"

                        return when (cell) {
                            is MineCell -> "*"
                            is SafeCell -> "${cell.countOfAdjacentMine}"
                        }
                    }
                )
            )
        }
    }
}
