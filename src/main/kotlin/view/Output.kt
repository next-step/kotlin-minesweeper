package view

import minesweeper.Message
import minesweeper.MinesweeperBoard

object Output {

    fun printAny(any: Any) {
        println(any)
    }

    fun printMinesweeperBoard(minesweeperBoard: MinesweeperBoard) {
        printAny(Message.OUTPUT_START_MINESWEEPER)
        printAny(fillingBoard(minesweeperBoard))
    }

    private fun fillingBoard(minesweeperBoard: MinesweeperBoard): String {
        val (row, col) = minesweeperBoard.gameBoard.let {
            it.height to it.width
        }
        val map = Array(row) { Array(col) { 'C' } }
        minesweeperBoard.mines.mines.forEach {
            map[it.x][it.y] = '*'
        }
        return map.joinToString("\n") { it.joinToString(" ") }
    }
}
