package minesweeper.view

import minesweeper.model.MineField

object ConsoleResultView : ResultView {
    override fun printMineField(field: MineField) {
        println("\n지뢰찾기 게임 시작")
        println(field)
    }
}
