package com.nextstep.jngcii.minesweeper.domain

@JvmInline
value class Row(val row: List<Boolean>) {
    companion object {
        fun from(boolean2dList: List<List<Boolean>>) = boolean2dList.map { Row(it) }
    }
}
