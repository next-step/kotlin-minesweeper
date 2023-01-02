package minesweeper.domain

class BlockCreator(
    private val width: Int,
    private val height: Int,
    private val mineCount: Int,
) {

    fun createBlocks(): List<List<Block>> = (createNormalBlocks(width, height, mineCount) + createMineBlocks(mineCount)).shuffled().chunked(width)

    private fun createNormalBlocks(width: Int, height: Int, mineCount: Int): List<Block.Normal> {
        val blockCount = height * width - mineCount
        return List(blockCount) { Block.Normal() }
    }

    private fun createMineBlocks(mineCount: Int): List<Block.LandMine> {
        return List(mineCount) { Block.LandMine }
    }
}
