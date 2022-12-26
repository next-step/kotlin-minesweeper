package minesweeper.view

import minesweeper.domain.Block
import minesweeper.domain.MineBlock
import minesweeper.domain.Point

object ResultView {

    private const val DEFAULT_BLOCK_CHARACTER = "C"
    private const val DEFAULT_MINE_CHARACTER = "*"
    private const val BLANK = " "

    fun renderInitialBoard(state: Map<Point, Block>) {
        println()
        println("지뢰찾기 게임 시작")
        renderBoard(state)
    }

    private fun renderBoard(state: Map<Point, Block>) {
        val yList = state.toList().groupBy { it.first.y }
        yList.forEach {
            renderRow(it.value.map { pair -> pair.second })
        }
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
