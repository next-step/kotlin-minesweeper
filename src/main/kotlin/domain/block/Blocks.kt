package domain.block

import domain.GameOverException
import java.util.LinkedList

data class Blocks(
    val values: List<Block>
) {
    fun isAllNormalBlocksOpened(): Boolean {
        return values.filter { it !is Mine }
            .all { !it.isClosed() }
    }

    fun open(position: Position): Blocks {
        val blockToOpen = findBlockIn(position)
        if (blockToOpen.isMine()) throw GameOverException("해당 칸은 지뢰입니다!")
        val visitedBlocks = visitBlocks(position)
        val blocks = findClosedBlocks(visitedBlocks) + visitedBlocks
        return Blocks(blocks.sortedBy { it.position })
    }

    private fun findClosedBlocks(visitedBlocks: List<Block>): List<Block> {
        val visitedPositions = visitedBlocks.map { it.position }
        return this.values
            .filter { !visitedPositions.contains(it.position) }
    }

    private fun findBlockIn(position: Position): Block {
        return values.find { it.isAt(position) }
            ?: throw IllegalArgumentException("${position}에 해당하는 칸을 찾을 수 없습니다.")
    }

    private fun visitBlocks(startPosition: Position): List<Block> {
        val positionsQueue = LinkedList<Position>(listOf(startPosition))
        val visitedBlocks = mutableListOf<Block>()
        while (positionsQueue.isNotEmpty()) {
            val position = positionsQueue.poll()
            val minesCount = countMinesAround(position)
            visitedBlocks.add(OpenedBlock(position, minesCount))
            val visitedPositions = combineVisitedPositions(visitedBlocks, positionsQueue)
            positionsQueue.addAll(getNextVisitPositions(position, minesCount) - visitedPositions)
        }
        return visitedBlocks
    }

    private fun countMinesAround(position: Position): Int {
        return findBlocksIn(position.surroundings())
            .count { it.isMine() }
    }

    private fun findBlocksIn(positions: List<Position>): List<Block> {
        return this.values.filter { positions.contains(it.position) }
    }

    private fun combineVisitedPositions(visitedBlocks: List<Block>, visitingPositions: List<Position>): List<Position> {
        return visitedBlocks.asSequence()
            .map { it.position }
            .plus(visitingPositions)
            .toList()
    }

    private fun getNextVisitPositions(position: Position, minesCount: Int): List<Position> {
        if (minesCount == 0) return filterInBlocks(position.surroundings())
        return emptyList()
    }

    private fun filterInBlocks(positions: List<Position>): List<Position> {
        return this.values.asSequence()
            .map { it.position }
            .filter { positions.contains(it) }
            .toList()
    }
}
