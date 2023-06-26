package minesweeper.util

import minesweeper.domain.MineLocationGenerator
import minesweeper.domain.MineLocations

object BinaryMineLocationGenerator : MineLocationGenerator {
    override fun generateLocation(height: Int, width: Int, mineCount: Int): MineLocations {
        val mineIndices = (0 until height * width).filter { it % 2 == 0 }
        return MineLocations.of(mineIndices, height, width)
    }
}
