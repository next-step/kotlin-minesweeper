package com.nextstep.jngcii.minesweeper.domain

class MineMapFactory(
    private val orderStrategy: OrderStrategy
) {
    fun create(
        meta: MineMapMeta,
        mineCount: Int
    ): MineMap {
        require(meta.totalArea >= mineCount) {
            "지뢰 갯수는 ${meta.totalArea}(${meta.rowCount} X ${meta.columnCount}) 보다 클 수 없습니다. (입력값 : $mineCount)"
        }

        val locations = Locations(meta).apply {
            pickMines(mineCount, orderStrategy)
        }

        return MineMap.build(locations)
    }
}
