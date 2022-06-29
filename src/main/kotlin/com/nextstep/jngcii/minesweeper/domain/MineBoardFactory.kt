package com.nextstep.jngcii.minesweeper.domain

class MineBoardFactory(
    private val orderStrategy: OrderStrategy
) {
    fun create(
        meta: MineBoardMeta,
        mineCount: Int
    ): MineBoard {
        require(meta.totalArea >= mineCount) {
            "지뢰 갯수는 ${meta.totalArea}(${meta.rowCount} X ${meta.columnCount}) 보다 클 수 없습니다. (입력값 : $mineCount)"
        }

        return MineBoard(meta).apply {
            pickMines(mineCount, orderStrategy)
        }
    }
}
