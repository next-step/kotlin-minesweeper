package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.CellState
import minesweeper.domain.CleanCell
import minesweeper.domain.MineCell

object OutputView {
    fun printMineBoard(mineBoard: List<Cell>) {
        println("지뢰찾기 게임 시작")
        mineBoard.groupBy { it.position.row }
            .forEach { column ->
                println(column.value.joinToString(separator = " ") {
                    mineCellAsString(it)
                })
            }
    }

    private fun mineCellAsString(cell: Cell) = when {
        cell.state == CellState.CLOSED -> Cell.CLOSED_SIGN
        cell is MineCell -> MineCell.MINE_SIGN
        cell is CleanCell -> cell.nearMineCount.toString()
        else -> throw IllegalStateException("유효하지 않은 Cell 입니다.")
    }

    fun printStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printResult(win: Boolean) {
        if (win) {
            println("Win Game.")
        } else {
            println("Lose Game.")
        }
    }
}
