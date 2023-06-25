package minesweeper.domain

import minesweeper.domain.flag.BlockState
import minesweeper.domain.flag.Flag
import minesweeper.domain.flag.MineFlag

class Block(val coordinate: Coordinate, private val flag: Flag) {

    var blockState: BlockState = BlockState.HIDDEN
        private set

    val hasMine: Boolean = flag is MineFlag

    fun open(): BlockState = if (blockState == BlockState.HIDDEN) {
        blockState = flag.blockState
        blockState
    } else {
        BlockState.ALREADY_OPEN
    }
}
