package minesweeper.domain

import minesweeper.inputdata.MineGameConfig

object BoomPositionMaker {

    fun makePosition(mineGameConfig: MineGameConfig): List<BoomPosition> {
        return List(mineGameConfig.getMapSize()) { it + 1 }
            .shuffled()
            .take(mineGameConfig.mineCount.size)
            .map { it.numberToBoomPosition(mineGameConfig.width.size) }
    }

    private fun Int.numberToBoomPosition(width: Int): BoomPosition {
        val row = this / width
        val column = this % width
        return BoomPosition(row, column)
    }
}
