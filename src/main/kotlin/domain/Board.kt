package domain

data class Board(
    private val blocks: Map<Position, Block>
) {

    constructor(blocks: List<Block>) : this(blocks = blocks.associateBy { it.position })

    fun getBlocks(): Map<Position, Block> = blocks

    fun getWidth(): Int = blocks.maxOf { it.key.x }

    fun getBlockByPosition(position: Position): Block? = blocks[position]

    fun open(position: Position): Board {
        val targetBlock = getBlockByPosition(position) ?: throw IllegalArgumentException("존재하지 않는 위치입니다.")
        if (targetBlock.isMine()) {
            return openAll()
        }

        val opendBlockList = blocks.values.map { if (it == targetBlock) it.open() else it }
        return Board(openSurroundings(opendBlockList, position))
    }

    private fun openAll(): Board = Board(blocks.map { it.value.open() })

    private tailrec fun openSurroundings(blockList: List<Block>, targetPosition: Position): List<Block> {
        var targetBlocks = filterOpenTargets(blockList, targetPosition)
        if (!targetBlocks.isAllOpen()) {
            return blockList
        }

        var result = blockList.map {
            if (targetBlocks.contains(it)) {
                it.open()
            } else {
                it
            }
        }

        targetBlocks.forEach { result = openSurroundings(result, it.position) }
        return result
    }

    private fun List<Block>.isAllOpen(): Boolean = this.isNotEmpty() && this.count { !it.isZero() } == 0

    private fun filterOpenTargets(blockList: List<Block>, targetPosition: Position): List<Block> {
        return blockList.filter { targetPosition.surroundings().contains(it.position) && !it.visible }
    }
}
