package minesweeper.domain.block

import minesweeper.domain.block.AdjacentMineCount.Companion.DEFAULT_ADJACENT_MINE_COUNT

data class EmptyBlock(
    override val position: Position,
    private val _adjacentMineCount: AdjacentMineCount = DEFAULT_ADJACENT_MINE_COUNT
) : Block(position) {
    override fun isMines(): Boolean = false

    override fun adjacentMineCount(): Int = _adjacentMineCount.adjacentMineCount
}
