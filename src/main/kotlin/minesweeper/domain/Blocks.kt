package minesweeper.domain

class Blocks(private val width: Int, private val height: Int, val blockBoard: Map<Int, List<Block>>) {

    data class Location(val x: Int, val y: Int)

    fun getAroundMineCount(block: Block.Normal): Int {
        val blockLocation = getBlockLocation(block)
        val aroundLocations = getAroundLocations(blockLocation.x, blockLocation.y)

        val aroundMines = aroundLocations.filter { location ->
            location?.let { blockBoard[it.y]?.get(it.x) } is Block.LandMine
        }

        return aroundMines.size
    }

    private fun getBlockLocation(block: Block.Normal): Location {
        var x = -1
        var y = -1

        blockBoard.keys.forEach {
            val blockX = blockBoard[it]?.indexOf(block) ?: -1
            if (blockX >= 0) {
                x = blockX
                y = it
                return@forEach
            }
        }
        require(x >= 0 && y >= 0) { "block not found" }
        return Location(x, y)
    }

    private fun getAroundLocations(x: Int, y: Int): List<Location?> {
        return listOf(
            createAroundLocation(x - 1, y),
            createAroundLocation(x - 1, y - 1),
            createAroundLocation(x - 1, y + 1),
            createAroundLocation(x + 1, y),
            createAroundLocation(x + 1, y - 1),
            createAroundLocation(x + 1, y + 1),
            createAroundLocation(x, y + 1),
            createAroundLocation(x, y - 1),
        )
    }

    private fun createAroundLocation(x: Int, y: Int): Location? {
        return if (x < 0 || y < 0 || y >= height || x >= width) null else Location(x, y)
    }
}
