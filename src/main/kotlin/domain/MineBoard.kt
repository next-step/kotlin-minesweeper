package domain

import domain.block.Block
import domain.block.Nothing

data class MineBoard(val width: Int, private val height: Int, val value: Map<Coordinate, Block>) {

    fun getSurroundingMineCountedBoard(): MineBoard {
        val board = this.value.toMutableMap()

        board
            .filter { !it.value.isMine() }
            .forEach { board[it.key] = calculateSurroundingMineCount(it.key) }

        return MineBoard(width, height, board)
    }

    private fun calculateSurroundingMineCount(coordinate: Coordinate): Block {
        val surroundingMineCount = coordinate.getSurroundingCoordinates(maxX = width, maxY = height)
            .count { value[it]?.isMine() ?: false }

        return Nothing(surroundingMineCount)
    }
}
