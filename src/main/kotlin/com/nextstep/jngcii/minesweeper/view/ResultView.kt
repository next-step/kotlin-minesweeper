package com.nextstep.jngcii.minesweeper.view

import com.nextstep.jngcii.minesweeper.domain.Location
import com.nextstep.jngcii.minesweeper.domain.MineBoard

object ResultView {
    private const val EMPTY_STRING = ""
    private const val MINE = "@"
    private const val SPACE = "-"

    fun printMap(mineBoard: MineBoard) {
        println("지뢰찾기 게임 시작")
        mineBoard.rowGroups.forEach {
            println(it.joinedStringBySymbol)
        }
    }

    private val List<Location>.joinedStringBySymbol
        get() = this.joinToString(EMPTY_STRING) {
            if (it.isMine) MINE else SPACE
        }
}
