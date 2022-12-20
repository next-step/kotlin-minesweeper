package minesweeper.view

import minesweeper.domain.Minefield

object ResultView {

    fun startGame(minefield: Minefield) {
        println("지뢰찾기 게임 시작")
        minefield.chunkedBoard.forEach {
            it.forEach { mine -> print(mine.mineType.shape) }
            println()
        }
    }
}
