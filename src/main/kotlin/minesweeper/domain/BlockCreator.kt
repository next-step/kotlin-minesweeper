package minesweeper.domain

class BlockCreator(
    height: Int,
    width: Int,
    mineCount: Int,
    val normalBlocks: List<Block> = createNormalBlocks(height, width, mineCount),
    val mineBlocks: List<Block> = createMineBlocks(mineCount)
) {

    companion object {
        private fun createNormalBlocks(height: Int, width: Int, mineCount: Int): List<Block.Normal> {
            val blockCount = height * width - mineCount
            return List(blockCount) { Block.Normal() }
        }

        private fun createMineBlocks(mineCount: Int): List<Block.LandMine> {
            return List(mineCount) { Block.LandMine() }
        }
    }
}
