package view

import domain.CellStatus
import domain.MineBoard
import domain.Row

object OutputView {
    const val MINE = "C "
    const val EMPTY = "* "

    fun printStartGame() {
        println()
        println("지뢰찾기 게임 시작")
    }

    fun printMineBoard(mineBoard: MineBoard) {
        mineBoard.info.layout.rows.forEach { row ->
            print(row)
            println()
        }
    }

    private fun print(row: Row) {
        for (cell in row.values) {
            if (cell.status == CellStatus.MINE) {
                print(MINE)
            } else {
                print(EMPTY)
            }
        }
    }
}
