package minesweeper.ui

import minesweeper.domain.HeightAndWidth
import minesweeper.domain.MineSweeperGame
import minesweeper.domain.Position
import minesweeper.domain.Size

object ResultView {

    private const val mine_symbol = "C"

    fun printMines(mineSweeperGame: MineSweeperGame, heightAndWidth: HeightAndWidth) {
        heightAndWidth.height
            .getNumbers()
            .forEach { printRow(it, heightAndWidth.width, mineSweeperGame) }
        println()
    }

    private fun printRow(rowNum: Size, width: Size, mineSweeperGame: MineSweeperGame) {
        width.getNumbers()
            .forEach {
                val position = Position(rowNum, it)
                printEachPosition(mineSweeperGame, position)
            }
        println()
    }

    private fun printEachPosition(mineSweeperGame: MineSweeperGame, position: Position) {
        when (mineSweeperGame.isOpen(position)) {
            true -> print("${mineSweeperGame.getAroundMinesCount(position)} ")
            false -> print("$mine_symbol ")
        }
    }

    fun printGameStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun printLoseGameMessage() {
        println("Lose Game.")
    }

    fun printWinGameMessage() {
        println("Win Game.")
    }
}
