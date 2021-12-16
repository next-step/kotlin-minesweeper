package minesweeper.ui

import global.strategy.ui.OutputStrategy
import global.util.FavoriteStringFixture.NEW_LINE
import minesweeper.domain.block.Block
import minesweeper.domain.block.Blocks
import minesweeper.domain.block.Position
import minesweeper.domain.board.Board
import minesweeper.domain.board.state.Finish
import minesweeper.domain.board.state.Lose
import minesweeper.domain.board.state.Win

class ResultView(private val outputStrategy: OutputStrategy) {

    fun startGame() = outputStrategy.execute(START_GAME)

    fun showBoard(board: Board) {
        val stringBuilder = board.blocks
            .blocks
            .values.fold(StringBuilder()) { sb, block -> sb.append(blockMapToMark(block, board)) }
        outputStrategy.execute(stringBuilder.toString())
    }

    fun showResult(board: Board) =
        when (board.gameState as Finish) {
            is Lose -> outputStrategy.execute(LOSE_GAME)
            is Win -> outputStrategy.execute(WIN_GAME)
        }

    private fun blockMapToMark(block: Block, board: Board): String =
        calculatePrefixNewLine(block.position, block.display(board.blocks))

    private fun calculatePrefixNewLine(position: Position, mark: String): String {
        if (position.isStartHorizontal()) {
            return NEW_LINE + mark
        }
        return mark
    }

    companion object {
        private const val START_GAME = "지뢰찾기 게임 시작"
        private const val WIN_GAME = "WIN Game."
        private const val LOSE_GAME = "LOSE Game."
        private const val MINE = 0x1F4A3
        private const val COVERED = 0x25FB

        private fun Block.display(blocks: Blocks): String {
            if (!isOpened()) {
                return COVERED.emoji()
            }
            if (isMine) {
                return MINE.emoji()
            }
            val block = blocks.findBlockByPosition(position)
            val adjacentBlocks = blocks.adjacentBlocks(position)
            return block.adjacentMineCount(adjacentBlocks).adjacentMineCount.toString()
        }
    }
}

private fun Int.emoji(): String = String(Character.toChars(this))
