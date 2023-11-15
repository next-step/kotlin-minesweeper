package minesweeper.view

import minesweeper.domain.MineSweeper

object OutputView {

    fun printMineSweeper(mineSweeper: MineSweeper) {
        println("지뢰찾기 게임 시작")
        for (i in 1..mineSweeper.mineMap.height()) {
            for (j in 1..mineSweeper.mineMap.width()) {
                if (mineSweeper.isMine(i, j)) {
                    print("*")
                } else {
                    print("C")
                }
            }
            println()
        }
    }
}
