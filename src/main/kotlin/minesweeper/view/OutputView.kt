package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.CleanCell
import minesweeper.domain.MineBoard
import minesweeper.domain.MineCell

object OutputView {
    fun printMineBoard(mineBoard: MineBoard) {
        println("지뢰찾기 게임 시작")
        mineBoard.mineCells.forEach { row ->
            println(row.mineCells.joinToString(separator = " ") {
                mineCellAsString(it)
            })
        }
    }

    private fun mineCellAsString(cell: Cell) = when (cell) {
        is MineCell -> cell.state
        is CleanCell -> cell.nearMineCount.toString()
    }
}
