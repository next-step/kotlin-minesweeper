package view

import domain.Block
import domain.Board
import domain.Position

object OutputView {

    private const val MARK_NOT_VISIBLE_BLOCK = "C"
    private const val MARK_MINE_BLOCK = "*"
    private const val SEPARATOR_ROW = "\n"
    private const val SEPARATOR_BLOCK = " "

    fun printGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        println(
            getBlocksAsView(board.getBlocks())
                .chunked(board.getWidth())
                .joinMineField()
        )
        println()
    }

    private fun getBlocksAsView(blocks: Map<Position, Block>): List<String> {
        return blocks.map {
            if (it.value.visible) {
                getBlockView(it.value)
            } else {
                MARK_NOT_VISIBLE_BLOCK
            }
        }
    }

    private fun List<List<String>>.joinMineField(): String {
        return this.joinToString(SEPARATOR_ROW) { it.joinToString(SEPARATOR_BLOCK) }
    }

    private fun getBlockView(block: Block): String {
        return if (block.isMine()) MARK_MINE_BLOCK else block.getMineCount().toString()
    }
}
