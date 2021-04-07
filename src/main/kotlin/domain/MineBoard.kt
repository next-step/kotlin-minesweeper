package domain

import domain.block.Block
import domain.block.Nothing

data class MineBoard(val width: Int, private val height: Int, val value: Map<Coordinate, Block>) {

    fun getSurroundingMineCountedBoard(): MineBoard {
        return MineBoard(width, height, value.entries.associate { generateBlock(it) })
    }

    private fun generateBlock(entry: Map.Entry<Coordinate, Block>): Pair<Coordinate, Block> {
        val key = entry.key
        val value = if (entry.value.isMine()) entry.value else calculateSurroundingMineCount(key)
        return key to value
    }

    private fun calculateSurroundingMineCount(coordinate: Coordinate): Block {
        val surroundingMineCount = coordinate.getSurroundingCoordinates(maxX = width, maxY = height)
            .count { value[it]?.isMine() ?: false }

        return Nothing(surroundingMineCount)
    }
}
