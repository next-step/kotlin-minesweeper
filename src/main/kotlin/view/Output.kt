package view

import minesweeper.MinesweeperBoard

object Output {

    private const val CELL: String = "C"
    private const val MINE: String = "*"

    fun printAny(any: Any) {
        println(any)
    }

    fun printMinesweeperBoard(minesweeperBoard: MinesweeperBoard) {
        printAny(fillingBoard(minesweeperBoard))
    }

    private fun fillingBoard(minesweeperBoard: MinesweeperBoard): String =
        minesweeperBoard.toBooleanBoard()
            .joinToString("\n") { fillingCell(it) }

    private fun fillingCell(row: Array<Boolean>) =
        row.joinToString(" ") { if (it) MINE else CELL }
}
