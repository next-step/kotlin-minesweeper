package minesweeper.domain

import minesweeper.inputdata.MineGameCondition

class MineMap(private val mineGameCondition: MineGameCondition, boomPositions: List<BoomPosition>) {

    private val map = Array(mineGameCondition.height.size) {
        Array(mineGameCondition.width.size) { Mine.SAFE }
    }

    init {
        boomPositions.forEach { map[it.row][it.column] = Mine.BOOM }
    }

    fun openMap(boomPosition: BoomPosition): Mine {
        return map[boomPosition.row][boomPosition.column]
    }

}
