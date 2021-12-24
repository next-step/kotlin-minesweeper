package minesweeper.ui

import global.strategy.ui.OutputStrategy
import global.util.FavoriteStringFixture.NEW_LINE
import minesweeper.domain.block.Block
import minesweeper.domain.block.Position
import minesweeper.domain.board.Board
import minesweeper.domain.board.state.Finish
import minesweeper.domain.board.state.Lose
import minesweeper.domain.board.state.Win
import java.util.Objects.isNull

class ResultView(private val outputStrategy: OutputStrategy) {

    fun startGame() = outputStrategy.execute(START_GAME)

    fun showBoard(board: Board) {
        val stringBuilder = board.blocks
            .blocks
            .keys.fold(StringBuilder()) { sb, position -> sb.append(blockMapToMark(position, board)) }
        outputStrategy.execute(stringBuilder.toString())
    }

    fun showResult(board: Board) =
        when (board.gameState as Finish) {
            is Lose -> outputStrategy.execute(LOSE_GAME)
            is Win -> outputStrategy.execute(WIN_GAME)
        }

    private fun blockMapToMark(position: Position, board: Board): String {
        val block = board.findBlock(position)
        val mark = block.display()
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

        private fun Block.display(): String {
            val mineCount = adjacentMineCount?.adjacentMineCount
            if (isNull(mineCount) || !isOpened()) {
                return COVERED.emoji()
            }
            if (isMine) {
                return MINE.emoji()
            }
            return mineCount.toString()
        }
    }
}

private fun Int.emoji(): String = String(Character.toChars(this))
