package minesweeper.view

import minesweeper.domain.Mines
import minesweeper.domain.Position

object OutputView {

    fun printMineSweeper(positions: List<Position>, mines: Mines) {
        println("지뢰찾기 게임 시작")
        positions.forEach {
            if (mines.isMine(it))
                return@forEach print("* ")

            print("C ")
        }
        println()
    }
}
