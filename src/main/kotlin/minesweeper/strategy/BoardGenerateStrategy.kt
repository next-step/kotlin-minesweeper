package minesweeper.strategy

import minesweeper.domain.block.Block

fun interface BoardGenerateStrategy {
    fun generate(width: Int, height: Int, minesCount: Int): List<Block>
}
