package minesweeper.domain

sealed interface Block {
    fun addNearBlocks(blocks: List<Block>)
    fun getNearbyMineCount(): Int
}

class CleanBlock(
    private val nearBlocks: MutableList<Block> = mutableListOf()
) : Block {
    override fun addNearBlocks(blocks: List<Block>) {
        check(nearBlocks.isEmpty())
        nearBlocks.addAll(blocks)
    }

    override fun getNearbyMineCount(): Int {
        check(nearBlocks.isNotEmpty())
        return nearBlocks.count { it is MineBlock }
    }
}

class MineBlock(
    private val nearBlocks: MutableList<Block> = mutableListOf()
) : Block {
    override fun addNearBlocks(blocks: List<Block>) {
        check(nearBlocks.isEmpty())
        nearBlocks.addAll(blocks)
    }
    override fun getNearbyMineCount(): Int {
        check(nearBlocks.isNotEmpty())
        return nearBlocks.count { it is MineBlock }
    }
}
