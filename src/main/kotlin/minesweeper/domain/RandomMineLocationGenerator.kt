package minesweeper.domain

object RandomMineLocationGenerator : MineLocationGenerator() {

    override fun getMineIndices(height: Int, width: Int, mineCount: Int): MineIndices {
        return MineIndices((0 until height * width).shuffled().take(mineCount))
    }
}
