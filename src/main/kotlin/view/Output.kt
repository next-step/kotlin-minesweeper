package view

import minesweeper.MinesweeperBoard

object Output {

    private const val INPUT_HEIGHT = "높이를 입력하세요."
    private const val INPUT_WIDTH = "너비를 입력하세요."
    private const val INPUT_MINES = "지뢰는 몇 개인가요?"
    private const val OUTPUT_START_MINESWEEPER = "지뢰찾기 게임 시작"
    private const val MINE: String = "*"

    fun printHeightMessage() {
        println(INPUT_HEIGHT)
    }

    fun printWidthMessage() {
        println(INPUT_WIDTH)
    }

    fun printMinesMessage() {
        println(INPUT_MINES)
    }

    fun printStartMessage() {
        println(OUTPUT_START_MINESWEEPER)
    }

    fun printMinesweeperBoard(minesweeperBoard: MinesweeperBoard) {
        println(fillingBoard(minesweeperBoard))
    }

    private fun fillingBoard(minesweeperBoard: MinesweeperBoard): String =
        minesweeperBoard.calculateAdjacentMineCounts()
            .joinToString("\n") { fillingCell(it) }

    private fun fillingCell(row: Array<Int>) =
        row.joinToString(" ") { if (it < 0) MINE else it.toString() }
}
