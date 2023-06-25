package minesweeper.domain

import minesweeper.domain.flag.BlockFlag
import minesweeper.domain.flag.Flag
import minesweeper.domain.flag.MineFlag

class Block(val coordinate: Coordinate, val flag: Flag) {

    val hasMine: Boolean = flag is MineFlag

    fun updateBlock(aroundMineCount: Int) = when (flag) {
        is BlockFlag -> flag.updateAroundMineCount(aroundMineCount = aroundMineCount)
        else -> Unit
    }
}
