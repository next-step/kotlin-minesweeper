package view

import domain.Block
import domain.BlockType
import domain.Board

object ResultView {
    private const val START_GAME = "지뢰찾기 게임 시작"
    private const val SYMBOL_HIDDEN: String = "?"
    private const val SYMBOL_MINE = "X"

    fun printStart(board: Board) {
        println(START_GAME)
        printBoard(board)
    }

    fun printBoard(board: Board) {
        board.boardInfo.entries.forEachIndexed { index, entry ->
            print(findSymbol(entry.value))
            print(if (index % board.width == board.width - 1) "\n" else " ")
        }
    }

    private fun findSymbol(block: Block) = when {
        block.type == BlockType.MINE -> SYMBOL_MINE
        block.isOpened -> block.mineCount.toString()
        else -> SYMBOL_HIDDEN
    }
}
