package ui

import domain.Mines
import model.GameData

object ResultView {
    fun printMineGameState(mines: Mines, gameData: GameData) {
        println("지뢰찾기 게임 시작")
        repeat(gameData.height) { row ->
            printRow(row, mines, gameData.width)
            println()
        }
    }

    private fun printRow(row: Int, mines: Mines, width: Int) {
        repeat(width) { col ->
            printCell(mines, row, col)
        }
    }

    private fun printCell(mines: Mines, row: Int, col: Int) {
        if (mines.isMine(row, col)) {
            print("* ")
        } else {
            print("C ")
        }
    }
}
