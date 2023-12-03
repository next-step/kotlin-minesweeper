package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Coordinate
import minesweeper.domain.EmptyCell
import minesweeper.domain.MineCell

object ConsoleOutput {
    fun startGame() {
        println("지뢰찾기 게임 시작")
    }

    fun loseGame() {
        println("Lose Game.")
    }

    fun printOpenedBoard(height: Int, width: Int, openedCoordinate: Set<Coordinate>, board: Board) {
        for (row in 0 until height) {
            for (col in 0 until width) {
                if (openedCoordinate.contains(Coordinate(row, col))) {
                    print("${board.countOf(row, col)} ")
                    continue
                }
                print("C ")
            }
            println()
        }
    }

    fun winGame(height: Int, width: Int, board: Board) {
        println("Win Game!!")

        for (row in 0 until height) {
            for (col in 0 until width) {
                val currentCell = board.at(row, col)
                val mark = when (currentCell) {
                    is MineCell -> "*"
                    is EmptyCell -> board.countOf(row, col).toString()
                }
                print("$mark ")
            }
            println()
        }
    }
}
