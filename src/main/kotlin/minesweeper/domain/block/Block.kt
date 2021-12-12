package minesweeper.domain.block

import minesweeper.domain.block.state.OpenState

sealed class Block(open val position: Position, open val openState: OpenState) {
    abstract val isMine: Boolean

    abstract val adjacentMineCount: AdjacentMineCount

    val isOpened: Boolean = openState.isOpened

    abstract fun open(): Block

    companion object {
        fun minesOrCell(position: Position, minePositions: List<Position>): Block {
            if (minePositions.contains(position)) {
                return MineBlock(position)
            }
            return EmptyBlock.of(position, minePositions)
        }
    }
}
