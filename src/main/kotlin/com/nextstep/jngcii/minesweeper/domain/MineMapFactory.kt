package com.nextstep.jngcii.minesweeper.domain

class MineMapFactory(
    private val pickStrategy: PickStrategy
) {
    fun create(
        rowCount: Int,
        columnCount: Int,
        mineCount: Int
    ): MineMap {
        val totalCount = rowCount * columnCount

        require(totalCount >= mineCount) {
            "지뢰 갯수는 $totalCount($rowCount X $columnCount) 보다 클 수 없습니다. (입력값 : $mineCount)"
        }

        val locations = Locations(rowCount, columnCount).apply {
            pickMines(mineCount, pickStrategy)
        }

        return MineMap.build(locations)
    }
}
