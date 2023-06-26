package minesweeper.domain

object RandomMineLocationGenerator : MineLocationGenerator {
    override fun generateLocation(height: Int, width: Int, mineCount: Int): MineLocations {
        val mineIndices = (0 until height * width).shuffled().take(mineCount)
        return MineLocations.of(mineIndices, height, width)
    }
}
