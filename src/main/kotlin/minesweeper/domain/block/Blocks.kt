package minesweeper.domain.block

data class Blocks(val blocks: List<Block>) {

    fun isMinePosition(position: Position): Boolean = findBlockByPosition(position).isMine

    fun findBlockByPosition(position: Position): Block = blocks.first { it.position == position }

    fun open(position: Position): Blocks = Blocks(blocks.map { if (it.position == position) it.open() else it })

    fun containsPosition(position: Position): Boolean = position in blocks.map { it.position }

    fun isEmptyBlocksAllOpened(): Boolean = blocks.filter { !it.isMine }.all { it.isOpened() }

    fun adjacentMineCount(position: Position): AdjacentMineCount {
        val minePositions = blocks.filter { it.isMine }
            .map { it.position }
        return AdjacentMineCount.from(
            MineSearchDirections.values()
                .map { it.nextCoordinate(position.x, position.y) }
                .count { Position(it.first, it.second) in minePositions }
        )
    }
}
