package minesweeper.domain

class Blocks(private val width: Int, private val height: Int, val blockBoard: List<List<Block>>) {

    data class Location(val x: Int, val y: Int)

    fun getAroundMineCount(block: Block.Normal): Int {
        val location = getBlockLocation(block)
        var aroundMineCount = 0

        if (getLeftBlock(location.x, location.y) is Block.LandMine) aroundMineCount++
        if (getLeftUpBlock(location.x, location.y) is Block.LandMine) aroundMineCount++
        if (getLeftDownBlock(location.x, location.y) is Block.LandMine) aroundMineCount++
        if (getRightBlock(location.x, location.y) is Block.LandMine) aroundMineCount++
        if (getRightUpBlock(location.x, location.y) is Block.LandMine) aroundMineCount++
        if (getRightDownBlock(location.x, location.y) is Block.LandMine) aroundMineCount++
        if (getUpBlock(location.x, location.y) is Block.LandMine) aroundMineCount++
        if (getDownBlock(location.x, location.y) is Block.LandMine) aroundMineCount++

        return aroundMineCount
    }

    private fun getBlockLocation(block: Block.Normal): Location {
        var x = -1
        var y = -1

        blockBoard.forEachIndexed { index, blocks ->
            val blockX = blocks.indexOf(block)
            if (blockX >= 0) {
                x = blockX
                y = index
                return@forEachIndexed
            }
        }

        require(x >= 0 && y >= 0) { "block not found" }
        return Location(x, y)
    }

    private fun getLeftBlock(x: Int, y: Int): Block? {
        val leftX = x - 1
        return if (leftX < 0) null else blockBoard[y][leftX]
    }

    private fun getLeftUpBlock(x: Int, y: Int): Block? {
        val leftX = x - 1
        val leftY = y - 1
        return if (leftX < 0 || leftY < 0) null else blockBoard[leftY][leftX]
    }

    private fun getLeftDownBlock(x: Int, y: Int): Block? {
        val leftX = x - 1
        val leftY = y + 1
        return if (leftX < 0 || leftY >= height) null else blockBoard[leftY][leftX]
    }

    private fun getRightBlock(x: Int, y: Int): Block? {
        val rightX = x + 1
        return if (rightX >= width) null else blockBoard[y][rightX]
    }

    private fun getRightUpBlock(x: Int, y: Int): Block? {
        val rightX = x + 1
        val rightY = y - 1
        return if (rightX >= width || rightY < 0) null else blockBoard[rightY][rightX]
    }

    private fun getRightDownBlock(x: Int, y: Int): Block? {
        val rightX = x + 1
        val rightY = y + 1
        return if (rightX >= width || rightY >= height) null else blockBoard[rightY][rightX]
    }

    private fun getUpBlock(x: Int, y: Int): Block? {
        val upY = y - 1
        return if (upY < 0) null else blockBoard[upY][x]
    }

    private fun getDownBlock(x: Int, y: Int): Block? {
        val downY = y + 1
        return if (downY >= height) null else blockBoard[downY][x]
    }
}
