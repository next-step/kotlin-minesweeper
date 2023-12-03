package minesweeper.view

import minesweeper.domain.*

object ConsoleOutput {
    fun startGame() {
        println("지뢰찾기 게임 시작")
    }

    fun loseGame() {
        println("Lose Game.")
    }

    fun printOpenedBoard(height: Int, width: Int, openedCoordinate: Set<Coordinate>, countingBoard: CountingBoard) {
        for (row in 0 until height) {
            for (col in 0 until width) {
                if (openedCoordinate.contains(Coordinate(row, col))) {
                    print("${countingBoard.countOf(row, col)} ")
                    continue
                }
                print("C ")
            }
            println()
        }
    }

    fun winGame(height: Int, width: Int, board: Board, countingBoard: CountingBoard) {
        println("Win Game!!")

        for (row in 0 until height) {
            for (col in 0 until width) {
                val currentCell = board.at(row, col)
                val mark = when (currentCell) {
                    is MineCell -> "*"
                    is EmptyCell -> countingBoard.countOf(row, col).toString()
                }
                print("$mark ")
            }
            println()
        }
    }
}
