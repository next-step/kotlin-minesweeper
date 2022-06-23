package com.nextstep.jngcii.minesweeper.domain

class MineCountsByRow private constructor(
    mineCounts: MutableList<Int>
) : MutableList<Int> by mineCounts {
    constructor(rowCount: Int) : this(MutableList(rowCount) { 0 })
}
