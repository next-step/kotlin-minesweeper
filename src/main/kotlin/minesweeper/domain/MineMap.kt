package minesweeper.domain

import minesweeper.inputdata.MineGameConfig

class MineMap(private val mineGameConfig: MineGameConfig, boomPositions: List<BoomPosition>) {

    private val map = Array(mineGameConfig.height.size) {
        Array(mineGameConfig.width.size) { Mine.SAFE }
    }

    init {
        boomPositions.forEach { map[it.row][it.column] = Mine.BOOM }
    }

    fun open(boomPosition: BoomPosition): Mine {
        val validateMapHeightInside = mineGameConfig.height.size > boomPosition.row && boomPosition.row >= 0
        val validateMapWidthInside = mineGameConfig.width.size > boomPosition.column && boomPosition.column >= 0
        require(validateMapHeightInside && validateMapWidthInside) { "맵을 열수 없습니다." }

        return map[boomPosition.row][boomPosition.column]
    }

    fun getMap(): Array<Array<Mine>> = map.copyOf()

}
