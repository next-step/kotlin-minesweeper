package ui

import domain.square.Square
import model.GameData

class ResultView(
    private val gameData: GameData
) {
    fun printMineGameState(squares: List<List<Square>>) {
        println("지뢰찾기 게임 시작")
        repeat(gameData.height) { row ->
            printRow(squares[row])
            println()
        }
    }

    private fun printRow(rowSquares: List<Square>) {
        repeat(gameData.width) { col ->
            printSquare(rowSquares[col])
        }
    }

    private fun printSquare(square: Square) {
        when (square.isMine) {
            true -> print("* ")
            false -> print("${square.mineCountAround} ")
        }
    }
}
