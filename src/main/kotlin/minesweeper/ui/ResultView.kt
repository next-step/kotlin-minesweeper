package minesweeper.ui

import minesweeper.domain.Block

interface ResultView {
    fun drawBlocks(blocks: List<List<Block>>)
}
