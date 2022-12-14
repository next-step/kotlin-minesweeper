package minesweeper

class BlockRepository(height: Int, width: Int, mineCount: Int, val blocks: List<List<Block>> = createBlocks(height, width, mineCount)) {

    companion object {
        private fun createBlocks(height: Int, width: Int, mineCount: Int): List<List<Block>> {
            val blockCount = height * width - mineCount
            val blockList = ArrayList<Block>()

            repeat(blockCount) {
                blockList.add(Block.Normal())
            }

            repeat(mineCount) {
                blockList.add(Block.LandMine())
            }

            return blockList.shuffled().chunked(width)
        }
    }
}
