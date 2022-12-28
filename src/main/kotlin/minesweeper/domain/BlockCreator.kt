package minesweeper.domain

class BlockCreator(
    height: Int,
    width: Int,
    mineCount: Int,
) {

    val blocks: List<Block> = (createNormalBlocks(height, width, mineCount) + createMineBlocks(mineCount)).shuffled()

    private fun createNormalBlocks(height: Int, width: Int, mineCount: Int): List<Block.Normal> {
        val blockCount = height * width - mineCount
        return List(blockCount) { Block.Normal }
    }

    private fun createMineBlocks(mineCount: Int): List<Block.LandMine> {
        return List(mineCount) { Block.LandMine }
    }
}
