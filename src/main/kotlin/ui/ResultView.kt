package ui

import domain.Mines
import domain.Position
import model.GameData

class ResultView(
    private val gameData: GameData
) {
    fun printMineGameState(mines: Mines) {
        println("지뢰찾기 게임 시작")
        repeat(gameData.height) { row ->
            printRow(row, mines)
            println()
        }
    }

    private fun printRow(row: Int, mines: Mines) {
        repeat(gameData.width) { col ->
            printCell(Position(row, col), mines)
        }
    }

    private fun printCell(position: Position, mines: Mines) {
        if (mines.isMine(position)) {
            print("* ")
        } else {
            print("C ")
        }
    }
}
