package minesweeper.domain.block

import minesweeper.domain.block.state.OpenState
import java.util.Objects.isNull

sealed class Block(private val openState: OpenState) {
    var adjacentMineCount: AdjacentMineCount? = null

    abstract val isMine: Boolean

    abstract fun open(): Block

    fun isOpened(): Boolean = openState.isOpened

    fun adjacentMineCount(adjacentBlocks: List<Block>): AdjacentMineCount {
        if (isNull(adjacentMineCount)) {
            val count = adjacentBlocks.count(Block::isMine)
            adjacentMineCount = AdjacentMineCount.from(count)
        }
        return adjacentMineCount as AdjacentMineCount
    }

    companion object {
        fun create(isMine: Boolean): Block {
            if (isMine) {
                return MineBlock()
            }
            return EmptyBlock()
        }
    }
}
