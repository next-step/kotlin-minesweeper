package minesweeper.view

import minesweeper.domain.Block
import minesweeper.domain.MineBlock

object ResultView {

    private const val DEFAULT_BLOCK_CHARACTER = "C"
    private const val DEFAULT_MINE_CHARACTER = "*"
    private const val BLANK = " "

    fun renderInitialBoard(state: List<List<Block>>) {
        println()
        println("지뢰찾기 게임 시작")
        renderBoard(state)
    }

    private fun renderBoard(state: List<List<Block>>) {
        state.forEach(::renderRow)
    }

    private fun renderRow(row: List<Block>) {
        row.forEach(::renderBlock)
        println()
    }

    private fun renderBlock(block: Block) {
        when (block) {
            is MineBlock -> print(DEFAULT_MINE_CHARACTER)
            else -> print(DEFAULT_BLOCK_CHARACTER)
        }
        print(BLANK)
    }
}
