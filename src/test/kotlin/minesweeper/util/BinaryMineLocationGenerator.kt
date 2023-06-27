package minesweeper.util

import minesweeper.domain.MineIndices
import minesweeper.domain.MineLocationGenerator

object BinaryMineLocationGenerator : MineLocationGenerator() {
    override fun getMineIndices(height: Int, width: Int, mineCount: Int): MineIndices {
        return MineIndices((0 until height * width).filter { it % 2 == 0 })
    }
}
