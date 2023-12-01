package minesweeper.view

import minesweeper.domain.GameBoard
import minesweeper.domain.MineSweeper

object ResultView {
    fun startMineSweeper(mineSweeper: MineSweeper){
       println("지뢰찾기 게임 시작")
       printMineSweeper(mineSweeper.gameBoard)
    }

    private fun printMineSweeper(gameBoard : GameBoard) {
        for (row in gameBoard.board) {
            for (cell in row) {
                print("$cell ")
            }
            println()
        }
    }
}
