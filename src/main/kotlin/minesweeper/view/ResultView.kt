package minesweeper.view

import minesweeper.domain.MineMap

class ResultView {

    fun outputGameStart(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        println(mineMap)
    }
}
