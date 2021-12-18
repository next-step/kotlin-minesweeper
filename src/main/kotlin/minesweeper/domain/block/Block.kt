package minesweeper.domain.block

import minesweeper.domain.block.state.OpenState

sealed class Block(open val openState: OpenState) {
    var adjacentMineCount: AdjacentMineCount? = null

    abstract val isMine: Boolean

    abstract fun open(): Block

    fun isOpened(): Boolean = openState.isOpened

    fun adjacentMineCount(adjacentBlocks: List<Block>): AdjacentMineCount {
        val count = adjacentBlocks.count(Block::isMine)
        adjacentMineCount = AdjacentMineCount.from(count)
        return adjacentMineCount as AdjacentMineCount
    }

    companion object {
        fun create(position: Position, minePositions: List<Position>): Block {
            if (minePositions.contains(position)) {
                return MineBlock()
            }
            return EmptyBlock()
        }
    }
}
