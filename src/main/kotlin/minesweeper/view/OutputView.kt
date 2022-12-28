package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.CleanCell
import minesweeper.domain.MineCell

object OutputView {
    fun printMineBoard(mineBoard: List<List<Cell>>) {
        println("지뢰찾기 게임 시작")
        mineBoard.forEach { row ->
            println(row.joinToString(separator = " ") {
                mineCellAsString(it)
            })
        }
    }

    private fun mineCellAsString(cell: Cell) = when (cell) {
        is MineCell -> cell.state
        is CleanCell -> cell.nearMineCount.toString()
    }
}
