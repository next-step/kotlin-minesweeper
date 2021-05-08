package ui

import domain.Row
import domain.square.Square
import model.GameData

class ResultView(
    private val gameData: GameData
) {
    fun printMineGameState(squares: List<Row>) {
        println("지뢰찾기 게임 시작")
        repeat(gameData.height) { rowNum ->
            printRow(squares[rowNum])
            println()
        }
    }

    private fun printRow(row: Row) {
        repeat(gameData.width) { colNum ->
            printSquare(row[colNum])
        }
    }

    private fun printSquare(square: Square) {

        if (!square.isOpen) {
            print("C ")
        } else if (!square.isMine) {
            print("${square.mineCountAround} ")
        }
    }
}
