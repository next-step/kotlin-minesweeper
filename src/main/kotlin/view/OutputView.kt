package view

import domain.Block
import domain.Position

object OutputView {
    fun drawMine(boardHeight: Int, boardWeight: Int, board: Map<Position, Block>) {
        println("지뢰찾기 게임 시작")

        (0 until boardHeight).forEach { height ->
            printRow(boardWeight, height, board)
            println()
        }
    }

    private fun printRow(boardWeight: Int, height: Int, board: Map<Position, Block>) {
        (0 until boardWeight).forEach { weight ->
            print(board[Position(height, weight)])
            print(" ")
        }
    }
}
