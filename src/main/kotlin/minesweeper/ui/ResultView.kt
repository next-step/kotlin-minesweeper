package minesweeper.ui

import minesweeper.domain.Blocks

interface ResultView {
    fun drawBlocks(blocks: Blocks)
}
