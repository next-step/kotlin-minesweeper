package com.nextstep.minesweeper.domain

enum class Icon(val symbol: Int) {
    BLANK(0),
    MINE(1);

    companion object {
        fun isMine(icon: Icon): Boolean = icon == MINE
    }
}
