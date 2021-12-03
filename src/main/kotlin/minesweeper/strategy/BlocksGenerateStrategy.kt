package minesweeper.strategy

import minesweeper.domain.MinesCount
import minesweeper.domain.Width
import minesweeper.domain.block.Block

fun interface BlocksGenerateStrategy {
    fun generate(width: Int, y: Int, minesCount: Int): List<Block>
}