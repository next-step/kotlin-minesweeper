package minesweeper.domain

import minesweeper.inputdata.MineGameConfig

class MineMap(private val mineGameConfig: MineGameConfig, boomPositions: List<BoomPosition>) {

    private val map = Array(mineGameConfig.height.size) {
        Array(mineGameConfig.width.size) { Mine.SAFE }
    }

    init {
        boomPositions.forEach { map[it.row][it.column] = Mine.BOOM }
    }

    fun openMap(boomPosition: BoomPosition): Mine {
        return map[boomPosition.row][boomPosition.column]
    }

}
