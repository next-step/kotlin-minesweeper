package minesweeper_refactor.domain.block

import minesweeper_refactor.domain.coordinate.Coordinate

class Block private constructor(val coordinate: Coordinate, val blockState: BlockState) {

    var isHidden: Boolean = true
        private set

    fun open(): OpenState = if (isHidden.not()) {
        OpenState.DO_NOTHING
    } else {
        isHidden = false
        OpenState.valueOf(blockState = blockState)
    }

    companion object {

        fun toNumberBlockOf(coordinate: Coordinate, aroundMineCount: Int): Block = Block(
            coordinate = coordinate,
            blockState = BlockState.valueOf(aroundMineCount = aroundMineCount),
        )

        fun toMineBlockFrom(coordinate: Coordinate): Block = Block(
            coordinate = coordinate,
            blockState = BlockState.MINE,
        )
    }
}
