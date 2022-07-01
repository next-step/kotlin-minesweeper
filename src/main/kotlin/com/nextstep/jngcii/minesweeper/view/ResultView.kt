package com.nextstep.jngcii.minesweeper.view

import com.nextstep.jngcii.minesweeper.domain.Location
import com.nextstep.jngcii.minesweeper.domain.MineBoard

object ResultView {
    private const val BLANK_STRING = " "
    private const val MINE = "@"

    fun printMap(mineBoard: MineBoard) {
        println("지뢰찾기 게임 시작")
        mineBoard.locationsByRow.forEach {
            println(it.joinedStringBySymbol)
        }
    }

    private val List<Location>.joinedStringBySymbol
        get() = this.joinToString(BLANK_STRING) {
            if (it.isMine) MINE else it.risk.toString()
        }
}
