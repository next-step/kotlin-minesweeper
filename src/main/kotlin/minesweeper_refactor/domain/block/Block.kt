package minesweeper_refactor.domain.block

import minesweeper_refactor.domain.coordinate.Coordinate

class Block(val coordinate: Coordinate, val blockState: BlockState) {

    var isHidden: Boolean = true
        private set

    fun open(): OpenState = if (isHidden.not()) {
        OpenState.DO_NOTHING
    } else {
        OpenState.valueOf(blockState = blockState)
    }
}
