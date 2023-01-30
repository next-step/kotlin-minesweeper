package domain

data class Board(
    private val blocks: Map<Position, Block>
) {

    constructor(blocks: List<Block>) : this(blocks = blocks.associateBy { it.position })

    fun getBlocks(): Map<Position, Block> = blocks
    fun getWidth(): Int = blocks.maxOf { it.key.x }
    fun getBlockByPosition(position: Position): Block? = blocks[position]
    fun isAllOpen(): Boolean = blocks.filterValues { !it.isMine() }.count { !it.value.isVisible } == 0
    fun isMine(position: Position): Boolean = blocks[position]?.isMine() == true

    fun open(position: Position): Board {
        val targetBlock = getBlockByPosition(position) ?: return this
        if (targetBlock.isVisible) {
            return this
        }
        if (targetBlock.isMine()) {
            return openAll()
        }

        val openedBlockList = blocks.values.toList().openBlock(targetBlock)
        return Board(openSurroundings(openedBlockList, targetBlock))
    }

    private tailrec fun openSurroundings(blockList: List<Block>, targetBlock: Block): List<Block> {
        if (!targetBlock.isZero()) {
            return blockList.openBlock(targetBlock)
        }

        val targetBlocks = surroundingsOpenTargets(blockList, targetBlock)
        var result = blockList
        targetBlocks.forEach { result = openSurroundings(result.openBlock(it), it) }
        return result
    }

    private fun surroundingsOpenTargets(blockList: List<Block>, targetBlock: Block): List<Block> {
        val surroundingsPositions = targetBlock.position.surroundings()
        return blockList.filter { surroundingsPositions.contains(it.position) && it.isOpenable() }
    }

    private fun openAll(): Board = Board(blocks.map { it.value.open() })

    private fun List<Block>.openBlock(targetBlock: Block): List<Block> {
        return this.map { if (it == targetBlock) it.open() else it }
    }
}
