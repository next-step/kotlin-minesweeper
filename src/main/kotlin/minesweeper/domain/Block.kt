package minesweeper.domain

import minesweeper.domain.flag.BlockFlag
import minesweeper.domain.flag.Flag

class Block(val coordinate: Coordinate, val flag: Flag) {

    fun updateBlock(aroundMineCount: Int) = when (flag) {
        is BlockFlag -> flag.updateAroundMineCount(aroundMineCount = aroundMineCount)
        else -> Unit
    }
}
