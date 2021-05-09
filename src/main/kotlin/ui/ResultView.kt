package ui

import domain.Board
import domain.Row
import domain.square.Square
import model.GameData

class ResultView(
    private val gameData: GameData
) {
    fun printStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        repeat(gameData.height) { rowNum ->
            printRow(board.squares.getRow(rowNum))
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

    fun printLose() {
        println("Lose Game.")
    }

    fun printWin() {
        println("Win Game.")
    }
}
