package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Mine
import minesweeper.domain.block.Position

data class Board(val area: Area, val blocks: List<Block>) {
    init {
        require(blocks.count { it is Mine } > MINIMUM_MINE_COUNT)
        require(area.getArea() == blocks.size)
    }

    fun findBlock(x: Int, y: Int): Block? {
        val targetPosition = Position(x, y)
        return blocks.find { it.getPosition() == targetPosition }
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 0
    }
}
