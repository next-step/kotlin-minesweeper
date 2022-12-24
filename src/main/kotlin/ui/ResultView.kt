package ui

import model.Cell
import model.MineSweeperGame

object ResultView {

    fun resultMineSweeperGame(mineSweeperGame: MineSweeperGame) {
        println("지뢰찾기 게임 시작")

        printRows(mineSweeperGame.getCells())
    }

    private fun printRows(cells: Array<Array<Cell>>) {
        cells.forEach { row ->
            printCol(row)
            println()
        }
    }

    private fun printCol(row: Array<Cell>) {
        row.forEach { col ->
            print("${col.name} ")
        }
    }
}
