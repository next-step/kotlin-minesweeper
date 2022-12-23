package view

import domain.Block
import domain.Board
import domain.Position

object OutputView {
    private const val MARK_MINE_BLOCK = "*"
    private const val SEPARATOR_ROW = "\n"
    private const val SEPARATOR_BLOCK = " "

    fun printGameStart(board: Board) {
        println("지뢰찾기 게임 시작")
        printBoard(board)
    }

    private fun printBoard(board: Board) {
        println(
            getBlocksAsView(board.getBlocks())
                .chunked(board.getWidth())
                .joinMineField()
        )
    }

    private fun getBlocksAsView(blocks: Map<Position, Block>): List<String> {
        return blocks.map { getBlockView(it.value) }
    }

    private fun List<List<String>>.joinMineField(): String {
        return this.joinToString(SEPARATOR_ROW) { it.joinToString(SEPARATOR_BLOCK) }
    }

    private fun getBlockView(block: Block): String {
        return if (block.isMine()) MARK_MINE_BLOCK else block.getMineCount().toString()
    }
}
