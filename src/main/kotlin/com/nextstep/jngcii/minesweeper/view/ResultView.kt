package com.nextstep.jngcii.minesweeper.view

import com.nextstep.jngcii.minesweeper.domain.MineMap
import com.nextstep.jngcii.minesweeper.domain.Row

object ResultView {
    private const val EMPTY_STRING = ""
    private const val MINE = "@"
    private const val SPACE = "-"

    fun printMap(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        mineMap.rows.forEach {
            println(it.joinedStringBySymbol)
        }
    }

    private val Row.joinedStringBySymbol
        get() = this
            .row
            .joinToString(EMPTY_STRING) {
                if (it.isMine) MINE else SPACE
            }
}
