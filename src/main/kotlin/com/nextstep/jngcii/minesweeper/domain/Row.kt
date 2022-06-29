package com.nextstep.jngcii.minesweeper.domain

data class Row(val row: List<RowElement>) : List<RowElement> by row {
    companion object {
        fun from(boolean2dList: List<List<Boolean>>) = boolean2dList.map {
            val elements = RowElement.from(it)
            Row(elements)
        }
    }
}
