package minesweeper.domain

sealed interface Block {
    fun addNearBlocks(blocks: List<Block>)
    fun getNearbyMineCount(): Int

    fun open(): GameState
    fun isOpen(): Boolean
}

class CleanBlock(
    private val nearBlocks: MutableList<Block> = mutableListOf(),
    private var open: Boolean = false
) : Block {
    override fun addNearBlocks(blocks: List<Block>) {
        check(nearBlocks.isEmpty())
        nearBlocks.addAll(blocks)
    }

    override fun getNearbyMineCount(): Int {
        check(nearBlocks.isNotEmpty())
        return nearBlocks.count { it is MineBlock }
    }

    override fun open(): GameState {
        if (open) return GameState.PLAYING
        open = true
        openNearBlocks()
        return GameState.PLAYING
    }

    private fun openNearBlocks() {
        if (nearBlocks.all { it is CleanBlock }) {
            nearBlocks.forEach { it.open() }
        }
    }

    override fun isOpen(): Boolean {
        return open
    }
}

class MineBlock(
    private val nearBlocks: MutableList<Block> = mutableListOf(),
    private var open: Boolean = false
) : Block {
    override fun addNearBlocks(blocks: List<Block>) {
        check(nearBlocks.isEmpty())
        nearBlocks.addAll(blocks)
    }
    override fun getNearbyMineCount(): Int {
        check(nearBlocks.isNotEmpty())
        return nearBlocks.count { it is MineBlock }
    }

    override fun open(): GameState {
        open = true
        return GameState.LOSE
    }

    override fun isOpen(): Boolean {
        return open
    }
}
