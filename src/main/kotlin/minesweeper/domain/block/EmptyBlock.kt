package minesweeper.domain.block

import minesweeper.domain.Directions
import minesweeper.domain.block.AdjacentMineCount.Companion.DEFAULT_ADJACENT_MINE_COUNT
import minesweeper.domain.block.Position.Companion.DEFAULT_X
import minesweeper.domain.block.Position.Companion.DEFAULT_Y

data class EmptyBlock(
    override val position: Position,
    private val _adjacentMineCount: AdjacentMineCount = DEFAULT_ADJACENT_MINE_COUNT
) : Block(position) {
    override val isMine: Boolean = false

    val adjacentMineCount: Int = _adjacentMineCount.adjacentMineCount

    companion object {
        fun of(position: Position, minePositions: List<Position>): Block {
            val adjacentMineCount = calculateMinesCount(position, minePositions)
            return EmptyBlock(position, AdjacentMineCount.from(adjacentMineCount))
        }

        private fun calculateMinesCount(position: Position, minePositions: List<Position>): Int =
            Directions.values()
                .map { directions -> directions.nextCoordinate(position.x, position.y) }
                .filter { it.first >= DEFAULT_X && it.second >= DEFAULT_Y }
                .count { Position(it.first, it.second) in minePositions }
    }
}
