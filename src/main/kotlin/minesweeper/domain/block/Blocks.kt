package minesweeper.domain.block

data class Blocks(val blocks: Map<Position, Block>) {

    fun isMinePosition(position: Position): Boolean = findBlockByPosition(position).isMine

    fun findBlockByPosition(position: Position): Block = blocks.getValue(position)

    fun open(position: Position): Blocks {
        val blocksMutableMap = blocks.toMutableMap()
        blocksMutableMap[position] = blocksMutableMap.getValue(position).open()
        return Blocks(blocksMutableMap.toMap())
    }

    fun containsPosition(position: Position): Boolean = position in blocks.keys

    fun isEmptyBlocksAllOpened(): Boolean = blocks.values.filter { !it.isMine }.all { it.isOpened() }

    fun adjacentBlocks(position: Position): List<Block> {
        return MineSearchDirections.values()
            .map { mapToNextPosition(it, position) }
            .filter(::containsPosition)
            .map { blocks.getValue(it) }
    }

    private fun mapToNextPosition(it: MineSearchDirections, position: Position): Position {
        val nextCoordination = it.nextCoordinate(position.x, position.y)
        return Position(nextCoordination.first, nextCoordination.second)
    }
}
