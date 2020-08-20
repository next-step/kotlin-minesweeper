package domain.block

import domain.GameOverException

data class Blocks(
    val values: List<Block>
) {
    fun isAllNormalBlocksOpened(): Boolean = findAllNonMineBlocks().all { !it.isClosed() }

    private fun findAllNonMineBlocks(): List<Block> = values.filter { it !is Mine }

    fun open(position: Position): Blocks {
        val blockToOpen = findBlockIn(position)
        if (blockToOpen.isMine()) throw GameOverException("해당 칸은 지뢰입니다!")
        val visitedPositions = visitPositions(listOf(position))
        return Blocks(createOpenedBlocks(visitedPositions))
    }

    private fun createOpenedBlocks(visitedPositions: List<Position>): List<Block> {
        val visitedBlocks = findBlocksIn(visitedPositions)
        val closedBlocks = this.values - visitedBlocks
        val openedBlocks = visitedBlocks.map { OpenedBlock(it.position, it.getMinesCount()) as Block }
        return (openedBlocks + closedBlocks).sortedBy { it.position }
    }

    private fun findBlockIn(position: Position): Block {
        return values.find { it.isAt(position) }
            ?: throw IllegalArgumentException("${position}에 해당하는 칸을 찾을 수 없습니다.")
    }

    private tailrec fun visitPositions(
        positionsToVisit: List<Position>,
        visitedPositions: List<Position> = emptyList()
    ): List<Position> {
        if (positionsToVisit.isEmpty()) return visitedPositions
        val newVisitedPositions = visitedPositions + positionsToVisit
        val nextVisitPositions = getNextVisitPositions(positionsToVisit) - newVisitedPositions
        return visitPositions(nextVisitPositions, newVisitedPositions)
    }

    private fun getNextVisitPositions(positions: List<Position>): List<Position> {
        return positions.flatMap { getNextVisitPositions(it) }.distinct()
    }

    private fun getNextVisitPositions(position: Position): List<Position> {
        val minesCount = countMinesAround(position)
        if (minesCount == 0) return filterInBlocks(position.surroundings())
        return emptyList()
    }

    private fun filterInBlocks(positions: List<Position>): List<Position> {
        return this.values.asSequence()
            .map { it.position }
            .filter { positions.contains(it) }
            .toList()
    }

    private fun countMinesAround(position: Position): Int {
        return findBlocksIn(position.surroundings())
            .count { it.isMine() }
    }

    private fun findBlocksIn(positions: List<Position>): List<Block> {
        return this.values.filter { positions.contains(it.position) }
    }
}
