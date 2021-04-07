package minesweeper.view

import minesweeper.domain.Mine

object OutputView {

    fun mineGameStart() = println("지뢰찾기 게임 시작")

    fun drawMap(maps: Array<Array<Mine>>) {
        for (minRow in maps) {
            println(minRow.joinToString(separator = "") { "${it.drawMine()} " })
        }
    }

    private fun Mine.drawMine(): String =
        when (this) {
            Mine.BOOM -> "*"
            Mine.SAFE -> "C"
        }
}
