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
        val openedBlocks = openBlocks(mutableListOf(position))
        return Blocks(replaceOpenedBlocks(openedBlocks))
    }

    private fun replaceOpenedBlocks(openedBlocks: List<Block>): List<Block> {
        val visitedPositions = openedBlocks.map { it.position }
        val closedBlocks = values.filter { !visitedPositions.contains(it.position) }
        return (closedBlocks + openedBlocks).sortedBy { it.position }
    }

    private fun findBlockIn(position: Position): Block {
        return values.find { it.isAt(position) }
            ?: throw IllegalArgumentException("${position}에 해당하는 칸을 찾을 수 없습니다.")
    }

    private tailrec fun openBlocks(
        positionsToVisit: MutableList<Position>,
        visitedBlocks: MutableList<Block> = mutableListOf()
    ): List<Block> {
        if (positionsToVisit.isEmpty()) return visitedBlocks
        val openedBlocks = createOpenedBlocks(positionsToVisit)
        visitedBlocks.addAll(openedBlocks)
        positionsToVisit.clear()
        positionsToVisit.addAll(getNextVisitPositions(openedBlocks, visitedBlocks))
        return openBlocks(positionsToVisit, visitedBlocks)
    }

    private fun createOpenedBlocks(positions: List<Position>): List<OpenedBlock> {
        return positions.map { OpenedBlock(it, countMinesAround(it)) }
    }

    private fun countMinesAround(position: Position): Int {
        return findBlocksIn(position.surroundings())
            .count { it.isMine() }
    }

    private fun findBlocksIn(positions: List<Position>): List<Block> {
        return this.values.filter { positions.contains(it.position) }
    }

    private fun getNextVisitPositions(blocks: List<OpenedBlock>, visitedBlocks: List<Block>): List<Position> {
        val visitedPositions = visitedBlocks.map { it.position }
        val positions = blocks.flatMap { getNextVisitPositions(it) }.distinct()
        return this.values.asSequence()
            .map { it.position }
            .filter { positions.contains(it) }
            .toList() - visitedPositions
    }

    private fun getNextVisitPositions(block: OpenedBlock): List<Position> {
        require(!block.isMine()) { "탐색하는 블럭이 지뢰일 수 없습니다. $block" }
        if (block.getMinesCount() == 0) return block.getSurroundingPositions()
        return emptyList()
    }
}
