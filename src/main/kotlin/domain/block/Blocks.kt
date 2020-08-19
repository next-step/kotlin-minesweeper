package domain.block

data class Blocks(
    val values: List<Block>
) {
    fun isMineIn(position: Position): Boolean {
        return getIn(position).isMine()
    }

    fun getIn(position: Position): Block {
        return values.find { it.isAt(position) }
            ?: throw IllegalArgumentException("${position}에 해당하는 칸을 찾을 수 없습니다.")
    }

    fun getSurroundingsOf(block: Block): List<Block> {
        val surroundPositions = block.getSurroundingPositions()
        return values.filter { surroundPositions.contains(it.position) }
    }

    fun openedOf(blocks: List<Block>): Blocks {
        val remainBlocks = this.values - blocks
        val newBlocks = remainBlocks + blocks.map { it.open() }
        return Blocks(newBlocks.sortedBy { it.position })
    }
}
