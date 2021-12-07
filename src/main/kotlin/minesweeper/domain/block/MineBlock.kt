package minesweeper.domain.block

import minesweeper.exception.NotCalculateAdjacentMineCountException

data class MineBlock(override val position: Position) : Block(position) {
    override fun isMines(): Boolean = true

    override fun adjacentMineCount(): Int = throw NotCalculateAdjacentMineCountException(this::class.java.toString())
}
