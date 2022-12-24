package com.nextstep.minesweeper.domain

class MineRow(width: Int) {
    private val row: Array<Mine> = Array(width) { Mine.GROUND }

    fun size(): Int {
        return row.size
    }

    fun dispense(col: Int) {
        row[col] = Mine.MINED
    }

    fun isMined(): Boolean {
        return row.any { it == Mine.MINED }
    }

    fun getRows(): Array<Mine> {
        return row.copyOf()
    }
}
