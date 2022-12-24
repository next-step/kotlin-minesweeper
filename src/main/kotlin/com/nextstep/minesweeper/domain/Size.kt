package com.nextstep.minesweeper.domain

class Size(val value: Int) {

    init {
        require(value > 0) { "size는 0보다 커야 합니다." }
    }

    fun multiple(height: Size): Size {
        return Size(value * height.value)
    }

    fun isGreaterThan(mineCounts: Int): Boolean {
        return value >= mineCounts
    }
}
