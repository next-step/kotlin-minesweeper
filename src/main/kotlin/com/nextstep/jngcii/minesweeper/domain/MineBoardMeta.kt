package com.nextstep.jngcii.minesweeper.domain

data class MineBoardMeta(
    val rowCount: Int,
    val columnCount: Int
) {
    init {
        require(rowCount > 0 && columnCount > 0) {
            "높이와 너비 모두 0보다 커야합니다. (높이:$rowCount, 너비:$columnCount)"
        }
    }

    val totalArea = rowCount * columnCount
}
